package scalangband.model.level.generation.terrain

import scalangband.model.level.generation.DungeonLevelCanvas
import scalangband.model.tile.Floor

import scala.util.Random

trait TerrainGenerator {
  def generate(random: Random, canvas: DungeonLevelCanvas): Unit

  def +(generator: TerrainGenerator): TerrainGenerator = new CompositeTerrainGenerator(Seq(this, generator))
}

class CompositeTerrainGenerator(val generators: Seq[TerrainGenerator]) extends TerrainGenerator {
  override def generate(random: Random, canvas: DungeonLevelCanvas): Unit = {
    generators.foreach(generator => generator.generate(random, canvas))
  }

  override def +(generator: TerrainGenerator): TerrainGenerator = generator match {
    case composite: CompositeTerrainGenerator => new CompositeTerrainGenerator(generators :++ composite.generators)
    case generator: TerrainGenerator => new CompositeTerrainGenerator(generators :+ generator)
  }
}

object EmptyFloorTerrainGenerator extends TerrainGenerator {
  override def generate(random: Random, canvas: DungeonLevelCanvas): Unit = {
    canvas.fillRect(2, 2, canvas.height - 4, canvas.width - 4, factory = () => Floor.empty())
  }
}