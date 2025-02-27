package scalangband.ui.gamepanel.overlay

import scalangband.ui.keys.{CloseActionFactory, DirectionKeyHandler, KeyHandler}

object CloseOverlay extends GamePanelOverlay {
  override def message: Option[String] = Some("Choose a direction...")
  override def keyHandler: KeyHandler = DirectionKeyHandler(CloseActionFactory)
  override def panel: Option[OverlayPane] = None
}
