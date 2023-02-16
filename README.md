Primera propuesta de pasos a seguir.

1. agregar aristas de la forma (node1, node2, peso)
2. encontrar el arbol recubridor minimo main_algorithms.Kruskal del grafo ingresado
3. dividir en 8 el peso del main_algorithms.Kruskal, para aproximar cuantos dias tomará entregar toda la mercancia, 
   solo habrá un repartidor
4. hacer n recorridos (subconjuntos) que serán similares en peso para los n dias correspondientes

Como abordar las restricciones dadas:

- cada dia el programa le entregará al repartidor las entregas y ruta ideal para ese dia

- si en un momento del dia se presenta algún inconveniente, se actualizará la ruta, y
de no alcanzar a pasar por un vertice se deja para el dia siguiente
