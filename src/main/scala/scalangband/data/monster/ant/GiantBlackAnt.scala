package scalangband.data.monster.ant

import scalangband.bridge.rendering.TextColors.DarkGrey
import scalangband.model.monster.action.{
  HearingBoundedAction,
  MeleeAttacks,
  MonsterActions,
  PathfindingAction,
  RandomMovementAction
}
import scalangband.model.monster.attack.BiteAttack
import scalangband.model.monster.{
  Ant,
  ArmoryInventoryGenerator,
  MonsterFactory,
  MonsterSpec,
  ProbabilisticInventoryGenerator
}
import scalangband.model.util.{DiceRoll, Weighted}

object GiantBlackAnt extends MonsterFactory {
  override val spec: MonsterSpec = MonsterSpec(
    name = "Giant Black Ant",
    archetype = Ant,
    depth = 2,
    health = DiceRoll("3d6"),
    hearing = 8,
    armorClass = 24,
    sleepiness = 80,
    experience = 8,
    actions = actions,
    color = DarkGrey
  )

  private def actions = MonsterActions(
    adjacent =
      Seq(Weighted(75, MeleeAttacks(new BiteAttack(DiceRoll("1d4")))), Weighted(25, RandomMovementAction)),
    los = Seq(Weighted(75, PathfindingAction), Weighted(25, RandomMovementAction)),
    otherwise = Seq(
      Weighted(75, HearingBoundedAction(PathfindingAction, RandomMovementAction)),
      Weighted(25, RandomMovementAction)
    )
  )

  private def inventory = Seq(
    new ProbabilisticInventoryGenerator(25, ArmoryInventoryGenerator(2))
  )

}
