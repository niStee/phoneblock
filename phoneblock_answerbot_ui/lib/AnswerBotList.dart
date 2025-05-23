import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:jsontool/jsontool.dart';
import 'package:phoneblock_answerbot_ui/base_path.dart'
  if (dart.library.html) 'package:phoneblock_answerbot_ui/base_path_web.dart';
import 'package:phoneblock_answerbot_ui/Api.dart';
import 'package:phoneblock_answerbot_ui/BotSetupForm.dart';
import 'package:phoneblock_answerbot_ui/CallListView.dart';
import 'package:phoneblock_answerbot_ui/ErrorDialog.dart';
import 'package:phoneblock_answerbot_ui/LoginScreen.dart';
import 'package:phoneblock_answerbot_ui/TitleRow.dart';
import 'package:phoneblock_answerbot_ui/httpAddons.dart';
import 'package:phoneblock_answerbot_ui/proto.dart';
import 'package:http/http.dart' as http;
import 'package:phoneblock_answerbot_ui/sendRequest.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:webview_flutter/webview_flutter.dart';

class AnswerBotList extends StatefulWidget {
  const AnswerBotList({super.key});

  @override
  State<StatefulWidget> createState() => AnswerBotListState();
}

class AnswerBotListState extends State<AnswerBotList> {

  bool loginRequired = false;
  String msg = 'Loading data...';

  List<AnswerbotInfo>? bots;

  @override
  void initState() {
    super.initState();
    requestBotList();
  }

  void requestBotList() async {
    var headers = await apiHeaders();
    if (kDebugMode) {
      debugPrint("Requesting bot list, authorization=${headers["Authorization"]}.");
    }
    http.get(Uri.parse('$basePath/ab/list'),
      headers: headers,
    ).then(processResponse);
  }

  void processResponse(http.Response response) {
    setState(() {
      if (response.statusCode == 401) {
        if (kDebugMode) {
          debugPrint("Unauthorized: ${response.body}");
        }

        loginRequired = true;
        return;
      }
      loginRequired = false;

      if (response.statusCode != 200) {
        msg = "Informationen können nicht abgerufen werden (Fehler ${response.statusCode}): ${response.body}";
        return;
      }

      if (response.contentType.mimeType != "application/json") {
        msg = "Informationen können nicht abgerufen werden (Content-Type: ${response.contentType.mimeType}).";
        return;
      }

      var bots = ListAnswerbotResponse.read(JsonReader.fromString(response.body)).bots;
      if (bots.isEmpty) {
        msg = "Du hast noch keinen Anrufbeantworter, klicke den Plus-Knopf unten, um einen PhoneBlock-Anrufbeantworter anzulegen.";
        this.bots = null;
      } else {
        this.bots = bots;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    if (loginRequired) {
      return Scaffold(
        appBar: AppBar(
          title: const TitleRow("Deine Anrufbeantworter"),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              const Text("Anmeldung erforderlich",
                style: TextStyle(fontSize: 20),
                textAlign: TextAlign.center,
              ),
              Padding(
                padding: const EdgeInsets.only(top: 16),
                child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      ElevatedButton(
                          onPressed: () async {
                            await login(context);
                            requestBotList();
                          },
                          child: const Text("Login")
                      )
                    ]
                ),
              )
            ],
          ),
        ),
      );
    }


    return Scaffold(
      appBar: AppBar(
        title: const TitleRow("Deine Anrufbeantworter"),
        actions: [
          IconButton(
            onPressed: () {
              setState(refreshBotList);
            },
            icon: const Icon(Icons.refresh),
          )
        ],
      ),
      body: _botList(context),
      floatingActionButton: FloatingActionButton(
        onPressed: () => _createAnswerBot(context),
        tooltip: 'Anrufbeantworter anlegen',
        child: const Icon(Icons.add),
      ),
    );
  }

  void _createAnswerBot(BuildContext context) async {
    http.Response response = await sendRequest(CreateAnswerBot());
    if (!context.mounted) return;

    if (response.statusCode != 200) {
      return showErrorDialog(context, response, 'Anlage fehlgeschlagen', "Der Anrufbeantworter konnte nicht angelegt werden");
    }

    var creation = CreateAnswerbotResponse.read(JsonReader.fromString(response.body));
    Navigator.push(context, MaterialPageRoute(builder: (context) => _setupAnswerBot(context, creation))).then((value) => refreshBotList());
  }

  Widget _setupAnswerBot(BuildContext context, CreateAnswerbotResponse creation) {
    return BotSetupForm(creation);
  }

  Widget _botList(BuildContext context) {
    var bots = this.bots;

    if (bots == null || bots.isEmpty) {
      return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Text(msg,
              style: const TextStyle(fontSize: 20),
              textAlign: TextAlign.center,
            )
          ],
        ),
      );
    }

    return ListView.separated(
      padding: const EdgeInsets.all(8),
      itemCount: bots.length,
      itemBuilder: (BuildContext context, int index) {
        var bot = bots[index];

        return SizedBox(
            height: 50,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Padding(padding: const EdgeInsets.only(right: 16),
                  child: Image.asset("assets/images/ab-logo-color-128.png"),
                ),
                Expanded(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Anrufbeantworter ${bot.userName}', overflow: TextOverflow.ellipsis, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 14),),
                      Text('${bot.newCalls} neue Anrufe, ${bot.callsAccepted} Anrufe, ${(bot.talkTime / 1000).round()} s Gesprächszeit gesamt', overflow: TextOverflow.ellipsis,),
                    ],
                  ),
                ),
                if (bot.enabled)
                  bot.registered ?
                  const Padding(padding: EdgeInsets.only(left: 16),
                      child: Chip(label: Text("aktiv"), backgroundColor: Colors.green, labelStyle: TextStyle(color: Colors.white),)) :
                  const Padding(padding: EdgeInsets.only(left: 16),
                      child: Chip(label: Text("verbinde..."), backgroundColor: Colors.orangeAccent, labelStyle: TextStyle(color: Colors.white),))
                else
                  setupComplete(bot) ?
                  const Padding(padding: EdgeInsets.only(left: 16),
                      child: Chip(label: Text("ausgeschaltet"), backgroundColor: Colors.black54, labelStyle: TextStyle(color: Colors.white),)) :
                  const Padding(padding: EdgeInsets.only(left: 16),
                      child: Chip(label: Text("unvollständig"), backgroundColor: Colors.black12, labelStyle: TextStyle(color: Colors.black),)),
                IconButton(
                  icon: const Icon(Icons.arrow_right),
                  iconSize: 32,
                  onPressed: () => showAnswerBot(context, bot),
                )
              ],
            )
        );
      },
      separatorBuilder: (BuildContext context, int index) => const Divider(),
    );
  }

  Future<void> login(BuildContext context) async {
    if (kIsWeb) {
      launchUrl(Uri.parse("$basePath/login?locationAfterLogin=/ab/"), webOnlyWindowName: "_self");
    } else {
      String authToken = await Navigator.push(context, MaterialPageRoute(builder: showLogin));
      await storeAuthToken(authToken);
    }
  }

  Widget showLogin(BuildContext context) {
    return LoginScreen();
  }

  bool setupComplete(AnswerbotInfo bot) => isSet(bot.host) || isSet(bot.ip4) || isSet(bot.ip6);

  showAnswerBot(BuildContext context, AnswerbotInfo bot) {
    var result = Navigator.push(context, MaterialPageRoute(builder: (context) =>
        setupComplete(bot) ?
          CallListView(bot) :
          BotSetupForm(
            CreateAnswerbotResponse(
              id: bot.id,
              userName: bot.userName,
              password: bot.password))));
    result.then((value) {
      refreshBotList();
    });
  }

  refreshBotList() {
    msg = 'Refreshing data...';
    requestBotList();
  }

  bool isSet(String? host) => host != null && host.isNotEmpty;
}

