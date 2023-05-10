import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:math';

void main() {
  runApp(TowerOfHanoiApp());
}

class TowerOfHanoiApp extends StatefulWidget {
  @override
  _TowerOfHanoiAppState createState() => _TowerOfHanoiAppState();
}

class _TowerOfHanoiAppState extends State<TowerOfHanoiApp> {
  int _currentIndex = 0;

  final List<Widget> _screens = [
    TowerOfHanoiScreen(),
    TowerOfHanoiRulesScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Tower of Hanoi',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Tower of Hanoi'),
        ),
        body: _screens[_currentIndex],
        bottomNavigationBar: BottomNavigationBar(
          currentIndex: _currentIndex,
          onTap: (index) {
            setState(() {
              _currentIndex = index;
            });
          },
          items: [
            BottomNavigationBarItem(
              icon: Icon(Icons.gamepad),
              label: 'Game',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.article),
              label: 'Rules',
            ),
          ],
        ),
      ),
    );
  }
}

class TowerOfHanoiRulesScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Rules:',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            Text(
              '1. The Tower of Hanoi consists of three pegs and a number of disks of different sizes.',
              style: TextStyle(fontSize: 16),
            ),
            SizedBox(height: 8),
            Text(
              '2. The puzzle starts with the disks in a stack in ascending order of size on one peg, the smallest at the top.',
              style: TextStyle(fontSize: 16),
            ),
            SizedBox(height: 8),
            Text(
              '3. The objective of the puzzle is to move the entire stack to another peg, obeying the following rules:',
              style: TextStyle(fontSize: 16),
            ),
            SizedBox(height: 8),
            Padding(
              padding: EdgeInsets.only(left: 16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    '- Only one disk can be moved at a time.',
                    style: TextStyle(fontSize: 16),
                  ),
                  SizedBox(height: 4),
                  Text(
                    '- Each move consists of taking the top disk from one of the stacks and placing it on top of another stack or on an empty peg.',
                    style: TextStyle(fontSize: 16),
                  ),
                  SizedBox(height: 4),
                  Text(
                    '- No disk may be placed on top of a smaller disk.',
                    style: TextStyle(fontSize: 16),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class TowerOfHanoiScreen extends StatefulWidget {
  @override
  _TowerOfHanoiScreenState createState() => _TowerOfHanoiScreenState();
}

class _TowerOfHanoiScreenState extends State<TowerOfHanoiScreen> {
  int _numberOfDisks = 3; // Initial number of disks
  List<TowerMove> _moves = [];
  int _currentMoveIndex = 0;
  final ScrollController _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();    
    _onNumberOfDisksChanged(_numberOfDisks);
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }


  Future<void> _fetchMoves(int numberOfDisks) async {
  final response =
      await http.get(Uri.parse('http://<domain>/api/moves/all?diskNumber=$numberOfDisks'));

  if (response.statusCode == 200) {
    Map<String, dynamic> jsonData = jsonDecode(response.body);
    TowerMoveResponse towerMoveReponse =  TowerMoveResponse.fromJson(jsonData);
    _moves = towerMoveReponse.towerMoves;
  } else {
    throw Exception('Failed to fetch Tower of Hanoi move');
  }
}

  void _scrollToLastMove() {
    _scrollController.animateTo(
      _scrollController.position.maxScrollExtent,
      duration: Duration(milliseconds: 500),
      curve: Curves.easeInOut,
    );
  }

  void _scrollToFirstMove() {
    _scrollController.animateTo(
      0,
      duration: Duration(milliseconds: 500),
      curve: Curves.easeInOut,
    );
  }

  void _onNumberOfDisksChanged(int? value) {
    _fetchMoves(value ?? 0).then((response) {
      setState(() {
      _numberOfDisks = value ?? 0;
      });
      _scrollToFirstMove();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Expanded(
            child: LayoutBuilder(
              builder: (context, constraints) {
                return SingleChildScrollView(
                  controller: _scrollController,
                  child: ConstrainedBox(
                    constraints: BoxConstraints(
                      minHeight: constraints.maxHeight,
                      minWidth: constraints.maxWidth,
                    ),
                    child: Column(
                      children: _moves
                          .map(
                            (move) => Container(
                              height: MediaQuery.of(context).size.height * .35,
                              width: MediaQuery.of(context).size.width * .9,
                              child: TowerOfHanoiCard(move: move, numberOfDisks: _numberOfDisks),
                            ),
                          )
                          .toList(),
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
      floatingActionButton: Stack(
        children: [
          Positioned(
            top: 20.0,
            right: 16.0,
            child: buildDiskDropDown(context),
          ),
          Positioned(
            bottom: 16.0,
            left: 40.0,
            child: FloatingActionButton(
              onPressed: _scrollToFirstMove,
              child: Icon(Icons.restart_alt_rounded),
            ),
          ),
          Positioned(
            bottom: 16.0,
            right: 16.0,
            child: FloatingActionButton(
              onPressed: _scrollToLastMove,
              child: Icon(Icons.check),
            ),
          ),
        ],
      ),
    );
  }

  Widget buildDiskDropDown(BuildContext context) {
    return DropdownButton<int>(
      icon: Icon(Icons.arrow_drop_down_circle),
      iconSize: 50,
      iconEnabledColor: Colors.blue,
      items: [
        DropdownMenuItem<int>(
          value: 1,
          child: Text('1'),
        ),
        DropdownMenuItem<int>(
          value: 2,
          child: Text('2'),
        ),
        DropdownMenuItem<int>(
          value: 3,
          child: Text('3'),
        ),
        DropdownMenuItem<int>(
          value: 4,
          child: Text('4'),
        ),
        DropdownMenuItem<int>(
          value: 5,
          child: Text('5'),
        ),
      ],
      onChanged: _onNumberOfDisksChanged,
    );
  }

}

class TowerOfHanoiCard extends StatelessWidget {
  final TowerMove move;
  final int numberOfDisks;

  num _totalNumberOfMoves(int value) {
    return pow(2, value) - 1;
  }

  const TowerOfHanoiCard({required this.move, required this.numberOfDisks});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      child: Card(
        child: Padding(
          padding: EdgeInsets.all(8),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              MoveNumberCircle(moveNumber: move.moveCount),
              Container (
                child: Row (
                  children: [
                    TowerOfHanoi(disks: move.sourceTower, numberOfDisks: numberOfDisks),
                    TowerOfHanoi(disks: move.auxiliaryTower, numberOfDisks: numberOfDisks),
                    TowerOfHanoi(disks: move.destinationTower, numberOfDisks: numberOfDisks),
                  ]
                ),
              ),
              LinearProgressIndicator(
                value: move.moveCount / _totalNumberOfMoves(numberOfDisks),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class TowerOfHanoi extends StatelessWidget {
  final List<int> disks;
  final int numberOfDisks;

  const TowerOfHanoi({required this.disks, required this.numberOfDisks});

  Widget buildTowerBase(int maxDisk) {
    return Container(
      width: 60,
      height: 2.0,
      color: Colors.black,
      margin: EdgeInsets.only(bottom: 4.0),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16.0),
      child: Column(
        children: [
          for (int i = (numberOfDisks - disks.length); i > 0 ; i--)
            Container(
              width:  12.0,
              height: 20.0,
              margin: EdgeInsets.only(bottom: 8.0),
              color: Colors.white,
          ),
          for (int i = 0; i < disks.length ; i++)
            Container(
              width: disks[i] * 12.0,
              height: 20.0,
              margin: EdgeInsets.only(bottom: 8.0),
              color: Colors.blue,
            ),
          buildTowerBase(numberOfDisks)
        ],
      ),
    );
  }
}

class MoveNumberCircle extends StatelessWidget {
  final int moveNumber;

  const MoveNumberCircle({required this.moveNumber});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 40.0,
      height: 40.0,
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        color: Colors.grey,
      ),
      child: Center(
        child: Text(
          moveNumber.toString(),
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
    );
  }
}

class TowerMove {

  int moveCount;
  List<int> sourceTower;
  List<int> auxiliaryTower;
  List<int> destinationTower;

  TowerMove({
    required this.moveCount,
    required this.sourceTower,
    required this.auxiliaryTower,
    required this.destinationTower,
  });

  factory TowerMove.fromJson(Map<String, dynamic> json) {
    return TowerMove(
      moveCount: json['moveCount'],
      sourceTower: List<int>.from(json['sourceTower']),
      auxiliaryTower: List<int>.from(json['auxiliaryTower']),
      destinationTower: List<int>.from(json['destinationTower']),
    );
  }
}

class TowerMoveResponse {
  List<TowerMove> towerMoves;

  TowerMoveResponse({
    required this.towerMoves,
  });

  factory TowerMoveResponse.fromJson(Map<String, dynamic> json) {
    final towerMovesJson = json['towerMoves'] as List<dynamic>;
    final towerMoves = towerMovesJson
        .map((towerMoveJson) => TowerMove.fromJson(towerMoveJson))
        .toList();

    return TowerMoveResponse(towerMoves: towerMoves);
  }
}