package scalangband.model.action

import scalangband.model.Game
import scalangband.model.action.result.{ActionResult, MessageResult, TrivialResult}
import scalangband.model.location.Direction
import scalangband.model.tile.{BrokenDoor, ClosedDoor, OpenDoor}

object PendingDirectionOpenAction extends DirectionNeededAction {
  override def withDirection(direction: Direction): GameAction = new OpenAction(direction)
}

class OpenAction(direction: Direction) extends GameAction {
  override def apply(game: Game): ActionResult = {

    val targetCoordinates = game.playerCoordinates + direction
    val targetTile = game.level(targetCoordinates)

    targetTile match {
      case _: ClosedDoor => 
        game.level.setTile(targetCoordinates, new OpenDoor(targetCoordinates, None))
        TrivialResult
      case _: BrokenDoor => MessageResult("The door is broken")
      case _ => MessageResult("There is nothing to open there")
    }

  }
}