package hr.element.post.api

object EntryPoint extends App {

  println(PostApi.getZip("Virovitica"))


  println(PostApi.findCities("Virovitica") mkString "\n")
}