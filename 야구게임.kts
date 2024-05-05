import kotlin.random.Random

fun main() {
    while (true) {
        // 정답 생성
        val answer = generateAnswer()

        // 게임 루프
        while (true) {
            println("숫자를 입력해 주세요 :")

            // 숫자 입력
            val guess = readLine()

            //// 입력이 널이거나 3자리가 아니거나 숫자가 아니면 다시 입력
            if (guess == null || guess.length != 3 || !guess.all { it.isDigit() }) {
                println("잘못된 입력입니다. 세 자리 숫자를 입력하세요.")
                println("--------------------")
                throw IllegalArgumentException("잘못된 입력입니다.")
                continue
            }

            // 정답 체크
            val result = check(answer, guess)
            println(result)
            println("--------------------")

            if (result == "3스트라이크") {
                println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
                println("--------------------")
                break
            }
        }

        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        val choice = readLine()

        // 게임 다시 시작 또는 종료 처리
        when (choice) {
            "1" -> continue
            "2" -> {
                println("게임을 종료합니다.")
                return
            }
            else -> {
                println("잘못된 입력입니다. 다시 입력하세요.")
                println("--------------------")
            }
        }
    }
}

// 정답 생성
fun generateAnswer(): String {
    val digits = ('1'..'9').toList()
    return digits.shuffled().take(3).joinToString("")
}

// 정답 체크
fun check(answer: String, guess: String): String {
    var strikes = 0
    var balls = 0

    for ((index, digit) in guess.withIndex()) {
        if (digit == answer[index]) {
            strikes++
        } else if (digit in answer) {
            balls++
        }
    }

    return when {
        strikes == 3 -> "3스트라이크"
        strikes > 0 && balls > 0 -> "${balls}볼 ${strikes}스트라이크"
        strikes > 0 -> "${strikes}스트라이크"
        balls > 0 -> "${balls}볼"
        else -> "아웃"
    }
}

main()