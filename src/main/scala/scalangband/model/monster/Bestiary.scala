package scalangband.model.monster

import org.slf4j.LoggerFactory
import scalangband.data.monster.ant.*
import scalangband.data.monster.bat.*
import scalangband.data.monster.bird.*
import scalangband.data.monster.canine.*
import scalangband.data.monster.centipede.*
import scalangband.data.monster.eye.*
import scalangband.data.monster.feline.*
import scalangband.data.monster.harpy.WhiteHarpy
import scalangband.data.monster.ickything.*
import scalangband.data.monster.jelly.*
import scalangband.data.monster.kobold.*
import scalangband.data.monster.mold.*
import scalangband.data.monster.mushroom.*
import scalangband.data.monster.person.*
import scalangband.data.monster.reptile.*
import scalangband.data.monster.rodent.*
import scalangband.data.monster.snake.*
import scalangband.data.monster.spider.*
import scalangband.data.monster.worm.*
import scalangband.data.monster.yeek.*
import scalangband.model.item.Armory
import scalangband.model.level.DungeonLevels
import scalangband.model.location.Coordinates

import scala.util.Random

class Bestiary(factories: Seq[MonsterFactory]) {
  private val factoriesByLevel = factories.map(factory => (factory.spec.depth, factory))
    .groupBy((level, _) => level)
    .map((level, seq) => (level, seq.map((_, factory) => factory).toIndexedSeq))

  def getMonsterFactory(random: Random, depth: Int): MonsterFactory = {
    val actualDepth = DungeonLevels(depth).monsters.randomInRange(random)
    val factoriesForLevel = factoriesByLevel(actualDepth)
    factoriesForLevel(random.nextInt(factoriesForLevel.size))
  }

  /**
   * Returns a monster factory (if any) for the given archetype at the provided depth.
   */
  def getMonsterFactory(random: Random, archetype: MonsterArchetype, depth: Int): Option[MonsterFactory] = {
    val factoriesForLevel = factoriesByLevel(depth)
    val archetypeFactories = factoriesForLevel.filter(factory => factory.spec.archetype == archetype)
    if (archetypeFactories.isEmpty) {
      None
    } else {
      Some(archetypeFactories(random.nextInt(archetypeFactories.size)))
    }
  }

  def generateMonster(random: Random, depth: Int, coordinates: Coordinates, armory: Armory): Monster = {
    getMonsterFactory(random, depth)(random, coordinates, armory)
  }
}
object Bestiary {
  private val Logger = LoggerFactory.getLogger(classOf[Bestiary])

  def apply(): Bestiary = new Bestiary(Seq(
    // these are in the same order as Angband's `monsters.txt`

    // Level 1
    GreyMold,
    GreyMushroomPatch,
    GiantYellowCentipede,
    GiantWhiteCentipede,
    WhiteIckyThing,
    ClearIckyThing,
    GiantWhiteMouse,
    LargeWhiteSnake,
    SmallKobold,
    WhiteWormMass,
    FloatingEye,
    RockLizard,
    WildDog,
    SoldierAnt,
    FruitBat,

    // Level 2
    Kobold,
    Soldier,
    Acolyte,
    WhiteJelly,
    GiantBlackAnt,
    WhiteHarpy,
    BlueYeek,
    CaveSpider,
    WildCat,
    Crow,

    // Level 3
    GiantWhiteAnt,
    MetallicRedCentipede,
 ))
}
