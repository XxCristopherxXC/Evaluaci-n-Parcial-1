import kotlinx.coroutines.*

fun main() = runBlocking {
    val gestor = GestorPedidos()
    gestor.inicializarCatalogo()

    var activo = true
    while (activo) {
        println("menu")
        println("1.Catalogo")
        println("2.Nuevo Pedido")
        println("3.Salir")
        print("Opción: ")
        when (readLine()) {
            "1" -> gestor.mostrarCatalogo()
            "2" -> {
                val pedido = Pedido("Regular")
                while (true) {
                    print("Ingrese ID del producto (0 para terminar): ")
                    val opcion = readLine()?.toIntOrNull()
                    if (opcion == null) {
                        println("Entrada inválida")
                        continue
                    }
                    if (opcion == 0) break
                    val producto = gestor.obtenerProductoPorId(opcion)
                    if (producto != null) {
                        pedido.agregarProducto(producto)
                        println("${producto.nombre} agregado")
                    } else {
                        println("Producto no encontrado")
                    }
                }
                println(pedido.obtenerResumen())

                println("Procesando pedido")
                procesarPedido(pedido)
            }
            "3" -> {
                println("Gracias por usar FoodExpress")
                activo = false
            }
            else -> println("Opción inválida")
        }
    }
}






