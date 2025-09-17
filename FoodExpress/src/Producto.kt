open class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val preparacion: Int
) {
    open fun mostrarInformacion(): String {
        return "ID: $id | $nombre - $$precio | Categoría: $categoria | Preparación: $preparacion min"
    }
    open fun calcularPrecioFinal(): Double {
        return precio
    }
}
class ProductoComida(
    id: Int,
    nombre: String,
    precio: Double,
    preparacion: Int,
    val gourmet: Boolean
) : Producto(id, nombre, precio, "Comida", preparacion) {
    override fun mostrarInformacion(): String {
        return super.mostrarInformacion() + if (gourmet) " (Gourmet)" else ""
    }
}

enum class TamanoBebida { PEQUENO, MEDIANO, GRANDE }

class ProductoBebida(
    id: Int,
    nombre: String,
    precio: Double,
    val tamano: TamanoBebida
) : Producto(id, nombre, precio, "Bebida", 0) {
    override fun mostrarInformacion(): String {
        return super.mostrarInformacion() + " | Tamaño: $tamano"
    }
}

class Pedido(
    val tipoCliente: String = "Regular",
    private val productos: MutableList<Producto> = mutableListOf()
) {
    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun calcularSubtotal(): Double {
        return productos.sumOf { it.precio }
    }

    fun calcularDescuento(): Double {
        val subtotal = calcularSubtotal()
        return when (tipoCliente) {
            "Premium" -> subtotal * 0.10
            "VIP" -> subtotal * 0.15
            else -> 0.0
        }
    }

    fun calcularTotal(): Double {
        return calcularSubtotal() - calcularDescuento()
    }

    fun calcularTiempoPreparacion(): Int {
        return productos.maxOfOrNull { it.preparacion } ?: 0
    }

    fun obtenerResumen(): String {
        var resumen = "=== RESUMEN DEL PEDIDO ===\n"
        resumen += "Cliente: $tipoCliente\n\n"
        resumen += "Productos:\n"
        for (p in productos) {
            resumen += " - ${p.nombre}: ${p.precio}\n"
        }
        resumen += "\nDetalle de costos:\n"
        resumen += " Subtotal: ${calcularSubtotal()}\n"
        resumen += " Descuento: ${calcularDescuento()}\n"
        resumen += " TOTAL: ${calcularTotal()}\n"
        resumen += "Tiempo estimado: ${calcularTiempoPreparacion()} min\n"
        return resumen
    }
}



