package EstructurasDeDatos.Arbol;

public class CreadorArbol {
    public static ArbolBinarioBusqueda matrizStringToABB(String[][] datos) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        if (datos == null) {
            return arbol;
        }
        for (String[] fila : datos) {
            if (fila == null || fila.length == 0) {
                continue;
            }
            String idStr = fila[0];
            if (idStr == null || idStr.isBlank()) {
                continue;
            }
            try {
                int id = Integer.parseInt(idStr.trim());
                arbol.insertar(id);
            } catch (NumberFormatException e) {
            }
        }
        return arbol;
    }
}
