import java.util.*

val table = arrayOf(
    arrayOf("  ", " 1 ", " 2 ", " 3 "),
    arrayOf("1 ", " - ", " - ", " - "),
    arrayOf("2 ", " - ", " - ", " - "),
    arrayOf("3 ", " - ", " - ", " - ")
)

var player: String? = "   "
fun inputTurn(p: String) {
    println("turn $p")
    print("Please input [row][col] : ")
    player = readLine().toString();
}

fun repeatInput(row: Int, col: Int): Boolean {
    return " - " != table[row][col]
}
fun setValue(p: String, row: Int, col: Int): Boolean {
//    println("this is $p row : $row col : $col")
    table[row][col] = " $p "
    return checkWin(row, col, p)
}

fun checkWin(row: Int, col: Int, p: String): Boolean {
    val Win = arrayOf("$row ", " $p ", " $p ", " $p ")
//    println("=====?>" + Arrays.toString(Win))
//    println("=====?>" + Arrays.deepToString(table))
    return if (Win contentEquals table[row] || Win contentEquals table[row]) {
        true
    } else if (" $p " == table[1][1] && table[1][1] == table[2][2] && " $p " == table[3][3]) {
        true
    } else if (" $p " == table[1][col] && " $p " == table[2][col] && " $p " == table[3][col]) {
        true
    } else " $p " == table[1][3] && table[1][3] == table[2][2] && " $p " == table[3][1]
}

fun main() {
    println("--- Welcome to OX game ---")
    ///เช็กจนชนะ
    //var win: Boolean = true
    var count: Int = 1
    var turn: String = "X"
    while (!(player.equals("*"))) {
        /// print table
        println("-------- ROUND $count --------")
        /////////////draw table
        for (row in table) {
            for (col in row) {
                print(col)
            }
            println()
        }
        //////call input
        inputTurn(turn)
        try {
            /////// convert int
            var row: Int = player?.substring(0..0)?.toInt()!!
            var col: Int = player?.substring(2..2)?.toInt()!!
            /////// check error input
            if ((row <= 0 || row > 3) || (col <= 0 || col > 3) || repeatInput(row, col)) {
                println("========input incorrect[1]=======")
            } else {
                if (setValue(turn, row, col)) {/////////////////////////has winner
                    println("** WINNER IS $turn **")
                    for (r in table) {
                        for (c in r) {
                            print(c)
                        }
                        println()
                    }
                    break;
                }
                count++
                turn = if (turn == "X") {
                    "O"
                } else {
                    "X"
                }
            }
        } catch (e: Exception) {
            println("========input incorrect[2]=======")
        }
        if (count == 10) {
            println("DRAW")
            for (row in table) {
                for (col in row) {
                    print(col)
                }
                println()
            }
            break
        }
    }
}