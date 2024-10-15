#include <vector>

typedef std::vector<std::pair<int, int>> vecPairIntInt;
const int PLAYER_X = 1;
const int PLAYER_O = -1;
const int EMPTY = 0;

class GameBoard {
public:
	std::vector<std::vector<int>> board;
	GameBoard() {
		board = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
	}
	~GameBoard() = default;
};

bool isWin(GameBoard& state, int player) {
	for (int i = 0; i < 3; i++) {
		if (state.board[0][i] == state.board[1][i] and
			state.board[1][i] == state.board[2][i] and state.board[0][i] == player) return true;
		if (state.board[i][0] == state.board[i][1] and
			state.board[i][1] == state.board[i][2] and state.board[i][0] == player) return true;
	}
	if (state.board[0][0] == state.board[1][1] and
		state.board[1][1] == state.board[2][2] and state.board[0][0] == player) return true;
	if (state.board[0][2] == state.board[1][1] and
		state.board[1][1] == state.board[2][0] and state.board[2][0] == player) return true;
}

bool isDraw(GameBoard& state) {
	if (!isWin(state, 0) and !isWin(state, 1)) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state.board[i][j] == EMPTY) return false;
			}
		}
		return true;
	}
	return false;
}

vecPairIntInt getAvaibleMoves(GameBoard& state) {
	vecPairIntInt unser;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (state.board[i][j] == EMPTY) unser.push_back(std::make_pair(i, j));
		}
	}
	return unser;
}

int algMiniMax(GameBoard& state, int depth, bool isMax) {
	if (isWin(state, PLAYER_X)) return 10 - depth;
	if (isWin(state, PLAYER_O)) return depth - 10;
	if (isDraw(state)) return 0;

	int value, maxEval = std::numeric_limits<int>::min();

	if (!isMax) {
		vecPairIntInt variants = getAvaibleMoves(state);
		for (auto pair : variants) {
			state.board[pair.first][pair.second] = PLAYER_X;
			value = algMiniMax(state, depth + 1, false);
			state.board[pair.first][pair.second] = EMPTY;
			maxEval = std::max(value, maxEval);
		}
	}
	return maxEval;
}

int main() {
}

