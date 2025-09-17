class GestorPedidos {
    private val catalogo = mutableListOf<Producto>()

    fun inicializarCatalogo() {
        catalogo.clear()
        // --- Comidas ---
        catalogo.add(ProductoComida(1, "Hamburguesa", 8990.0, 12, false))
        catalogo.add(ProductoComida(2, "Salmón", 15990.0, 20, true))
        catalogo.add(ProductoComida(3, "Pizza Napolitana", 11990.0, 15, false))
        catalogo.add(ProductoComida(4, "Huevos con Tocino", 13990.0, 25, true))

        // --- Bebidas ---
        catalogo.add(ProductoBebida(5, "Coca Cola", 1990.0, TamanoBebida.MEDIANO))
        catalogo.add(ProductoBebida(6, "Jugo Natural", 2990.0, TamanoBebida.GRANDE))
        catalogo.add(ProductoBebida(7, "Agua Mineral", 1490.0, TamanoBebida.PEQUENO))
        catalogo.add(ProductoBebida(8, "Limonada Especial", 3490.0, TamanoBebida.GRANDE))
    }

    fun mostrarCatalogo() {
        println("=== CATÁLOGO ===")
        catalogo.forEach { println(it.mostrarInformacion()) }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return catalogo.find { it.id == id }
    }
}



