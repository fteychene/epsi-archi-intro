package epsi.archi.intro.vote.functionnal

class Vote(val choices: List[String], val votes: Map[String, Int] = Map()) {

  def vote(vote : Option[(String, Int)]): Vote = vote match {
    case Some((id, value)) => new Vote(choices, votes + (id -> value))
    case None => new Vote(choices, votes)
  }

  def counters(): Map[String, Int] = votes.foldLeft(Map[String, Int]())((acc, kv) => acc + (choices(kv._2) -> (1 + acc.getOrElse(choices(kv._2), 0))))

  def close(): String = counters().maxBy(kv => kv._2)._1

  def votesWithValues(): Map[String, String] = votes.map(kv => kv._1 -> choices(kv._2))

}
