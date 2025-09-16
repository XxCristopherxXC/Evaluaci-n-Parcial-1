import kotlinx.coroutines.*

open class EstadoPedido

class Pendiente : EstadoPedido()
class EnPreparacion(val tiempo: Int, val progreso: Int) : EstadoPedido()
class Listo : EstadoPedido()
class Entregado(val tiempo: Int) : EstadoPedido()
class Cancelado(val motivo: String) : EstadoPedido()
class Error(val mensaje: String) : EstadoPedido()

fun mostrarEstado(estado: EstadoPedido): String {
    return when (estado) {
        is Pendiente -> "Pendiente - El pedido está pendiente"
        is EnPreparacion -> "En Preparación - ${estado.tiempo} min, ${estado.progreso}%"
        is Listo -> "Listo - El pedido está listo"
        is Entregado -> "Entregado - Pedido entregado en ${estado.tiempo} min"
        is Cancelado -> "Cancelado - Motivo: ${estado.motivo}"
        is Error -> "Error - ${estado.mensaje}"
        else -> "Estado desconocido"
    }
}

suspend fun procesarPedido(pedido: Pedido) {
    var estado: EstadoPedido = Pendiente()
    println(mostrarEstado(estado))
    delay(3000)

    estado = EnPreparacion(pedido.calcularTiempoPreparacion(), 50)
    println(mostrarEstado(estado))
    delay(3000)

    estado = Listo()
    println(mostrarEstado(estado))
    delay(3000)

    estado = Entregado(pedido.calcularTiempoPreparacion())
    println(mostrarEstado(estado))
}


