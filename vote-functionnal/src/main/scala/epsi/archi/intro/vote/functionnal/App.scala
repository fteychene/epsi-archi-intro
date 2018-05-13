package epsi.archi.intro.vote.functionnal

import scala.io.StdIn._

object App {

  def createChoices(): List[String] = {
    print("New choice (N to stop) : ")
    readLine() match {
      case "N" => List()
      case input => input :: createChoices()
    }
  }

  def displayVotes(vote: Vote) = vote.votesWithValues().foreach(kv => println(s"${kv._1} voted for ${kv._2}"))

  def askVote(choices: List[String]): Option[(String, Int)] = {
    print("Identifiant : ")
    val id = readLine()
    println("Choices : ")
    choices.zipWithIndex.foreach{ case (e, i) => println(i+" - "+e) }
    print("Choice : ")
    val choice = readInt();
    if (choice >= 0 && choice < choices.size) {
      Some((id, choice))
    } else {
      None
    }
  }

  def executeVote(vote: Vote): Vote = {
    println(
      """
        |Choose your action :
        |1 - Vote
        |2 - Display
        |3 - Close""".stripMargin)
    print("Choose your action : ")
    println()
    readInt() match {
      case 1 => executeVote(vote.vote(askVote(vote.choices)))
      case 2 => displayVotes(vote); executeVote(vote)
      case 3 => vote
      case _ => executeVote(vote)
    }
  }

  def main(args: Array[String]) = {
    println("Define the choices")
    val choices = createChoices()
    val vote = executeVote(new Vote(choices))
    println(s"Vote result : ${vote.close()}")
  }

}
