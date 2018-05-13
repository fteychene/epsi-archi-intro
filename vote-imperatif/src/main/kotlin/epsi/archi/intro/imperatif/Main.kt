package epsi.archi.intro.imperatif

var choices: List<String> = ArrayList<String>()
var votes: MutableMap<String, Int> = HashMap<String, Int>()

fun createChoices() {
    println("Define the choices")
    var input: String = ""
    do {
        print("New choice (N to stop) : ")
        input = readLine()!!.trim()
        if (input != "N") {
            choices += input
        }
    } while (input != "N")
}

fun addVote() {
    print("Identifiant : ")
    var identifant = readLine()!!.trim()
    println("Choices :")
    for ((index, value) in choices.withIndex()) {
        println("$index - $value")
    }
    print("Vote for : ")
    var index = Integer.valueOf(readLine()!!.trim())
    if (index >= 0 && index < choices.size) {
        votes.put(identifant, index)
    } else {
        println("Choix inconnu")
    }
}

fun displayVotes() {
    println("Votes")
    for ((id, choice) in votes) {
        println("$id voted for ${choices[choice]}")
    }
    println()
    println("Press any key to continue ...")
    readLine()
}

fun closeVote() {
    var counters: MutableMap<Int, Int> = HashMap<Int, Int>()
    for ((_, choice) in votes) {
        counters[choice] = counters.getOrDefault(choice, 0) + 1
    }
    var max = 0
    var result = -1
    for ((index, counter) in counters) {
        if (counter > max) {
            result = index
            max = counter
        }
    }
    if (result == -1) {
        println("Can't compute the winner")
    } else {
        println("Winner is ${choices[result]}")
    }
}

fun main(args: Array<String>) {
    createChoices()

    println()
    println()

    var choice: String = ""
    do {
        println("""
                Choose your action :
                1 - Vote
                2 - Display
                3 - Close
                """.trimIndent())
        print("Choose your action : ")
        choice = readLine()!!.trim()
        when (choice) {
            "1" -> addVote()
            "2" -> displayVotes()
            "3" -> closeVote()
            else -> println("Choix non reconnu")
        }
        println()
        println()
    } while (choice != "3")
}