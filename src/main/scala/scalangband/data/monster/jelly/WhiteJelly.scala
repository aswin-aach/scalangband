package scalangband.data.monster.jelly

import scalangband.bridge.rendering.TextColors.White
import scalangband.model.effect.Poisoning
import scalangband.model.element.Poison
import scalangband.model.monster.action.{MeleeAttacksAction, MonsterActions, MonsterPassAction}
import scalangband.model.monster.attack.TouchAttack
import scalangband.model.monster.{Jelly, MonsterFactory, MonsterSpec}
import scalangband.model.util.{DiceRoll, Weighted}

object WhiteJelly extends MonsterFactory {
  override val spec: MonsterSpec = MonsterSpec(
    name = "White Jelly",
    archetype = Jelly,
    depth = 2,
    health = DiceRoll("12d5"),
    speed = 40,
    armorClass = 1,
    experience = 10,
    sleepiness = 99,
    actions = actions,
    color = White
  )
  
  def actions = MonsterActions(
    adjacent = Seq(
      Weighted(100, MeleeAttacksAction(new TouchAttack(DiceRoll("1d2"), Some(Poison), Some(Poisoning(DiceRoll("1d2"))))))
    ), 
    otherwise = Seq(Weighted(100, MonsterPassAction)))
}
