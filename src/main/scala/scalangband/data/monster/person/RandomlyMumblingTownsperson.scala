package scalangband.data.monster.person

import scalangband.bridge.rendering.TextColors
import scalangband.data.item.money.CopperCoins
import scalangband.model.monster.*
import scalangband.model.monster.action.{MonsterActions, MonsterPassAction, RandomMovementAction, SpeakAction}
import scalangband.model.util.{DiceRoll, Weighted}

object RandomlyMumblingTownsperson extends MonsterFactory {
  override val spec: MonsterSpec = new MonsterSpec(
    name = "Randomly Mumbling Townsperson",
    archetype = Person,
    depth = 0,
    health = DiceRoll("1d4"),
    hearing = 20,
    armorClass = 1,
    sleepiness = 0,
    experience = 0,
    actions = actions,
    inventory = inventory,
    color = TextColors.White
  )

  private def actions = MonsterActions(
    adjacent = Seq(
      Weighted(90, MonsterPassAction),
      Weighted(9, RandomMovementAction),
      Weighted(1, SpeakAction("The townsperson mumbles incoherently."))
    ),
    los = Seq(
      Weighted(90, MonsterPassAction),
      Weighted(9, RandomMovementAction),
      Weighted(1, SpeakAction("The townsperson mumbles incoherently."))
    ),
    otherwise = Seq(
      Weighted(90, MonsterPassAction),
      Weighted(9, RandomMovementAction),
      Weighted(1, SpeakAction("The townsperson mumbles incoherently."))
    )
  )

  def inventory: Seq[MonsterInventoryGenerator] = Seq(
    new FixedInventoryGenerator(CopperCoins),
  )
}
