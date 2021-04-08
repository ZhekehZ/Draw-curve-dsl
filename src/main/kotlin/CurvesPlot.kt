import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.lang.Integer.min
import javax.swing.JFrame
import javax.swing.JPanel

class CurvesPlot(
    private val curves: List<Curve>,
    private val scale: Double,
    private val quality: Int,
    private val color: Color = Color.WHITE
) : JPanel() {

    private val useColor = color != Color.WHITE

    private val colors = arrayOf(
        Color.BLUE, Color.RED, Color.GREEN,
        Color.CYAN, Color.PINK, Color.ORANGE,
        Color.MAGENTA, Color.YELLOW
    )

    private fun IntRange.split() = flatMap { i ->
        (0 until quality)
            .map { i + it.toDouble() / quality }
    }
        .dropLast(quality - 1)

    override fun paint(g: Graphics) {
        val graphic2d = g as Graphics2D
        graphic2d.stroke = BasicStroke(3F)

        if (useColor) {
            graphic2d.color = color
        }

        val h = super.getHeight()
        val w = super.getWidth()
        val size = min(w, h)
        val toPlotScale: (Double) -> Int = { (it * size * scale).toInt() }

        val xc = curves.sumBy { it.range.map(it.x::invoke).sumBy(toPlotScale) } /
                curves.sumBy { it.range.count() }

        val yc = curves.sumBy { it.range.map(it.y::invoke).sumBy(toPlotScale) } /
                curves.sumBy { it.range.count() }

        curves.forEachIndexed { idx, c ->
            if (!useColor) {
                graphic2d.color = colors[idx % colors.size]
            }

            val xs = c.range.split().map(c.x::invoke).map(toPlotScale)
            val ys = c.range.split().map(c.y::invoke).map(toPlotScale)
            graphic2d.drawPolyline(
                xs.map { xc - it + w / 2 }.toIntArray(),
                ys.map { yc - it + h / 2 }.toIntArray(),
                xs.size
            )
        }
    }

    fun draw(width: Int = 1000, height: Int = 1000) {
        val frame = JFrame("Curves")
        frame.add(this)
        frame.setSize(width, height)
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
}
