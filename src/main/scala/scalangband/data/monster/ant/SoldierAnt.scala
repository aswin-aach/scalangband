package scalangband.data.monster.ant

import scalangband.bridge.rendering.TextColors.White
import scalangband.model.monster.action.{MeleeAttacksAction, MonsterActions, RandomMovementAction}
import scalangband.model.monster.attack.BiteAttack
import scalangband.model.monster.{Ant, MonsterFactory, MonsterSpec}
import scalangband.model.util.{DiceRoll, Weighted}

object SoldierAnt extends MonsterFactory {
  override val spec: MonsterSpec = MonsterSpec(
    name = "Soldier Ant",
    archetype = Ant,
    depth = 1,
    health = DiceRoll("2d5"),
    armorClass = 4,
    experience = 3,
    sleepiness = 40,
    actions = actions,
    color = White
  )

  private def actions = new MonsterActions(
    adjacent = Seq(Weighted(100, MeleeAttacksAction(new BiteAttack(DiceRoll("1d2"))))),
    otherwise = Seq(Weighted(100, RandomMovementAction))
  )

}
