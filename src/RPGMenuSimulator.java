import java.util.Scanner;

public class RPGMenuSimulator {
    public static void main(String[] args) {

        // Variables principales
        Scanner sc = new Scanner(System.in);

        String nombre = "";
        String clase = "";
        int vida = 0;
        int fuerza = 0;
        double oro = 0.0;
        boolean personajeCreado = false;

        // Inventario (solo para foreach)
        String[] inventario = {"Poción", "Hierro", "Pergamino", "Llave Antigua"};

        int opcionMenu = 0;


        //MENÚ PRINCIPAL (do-while)
        do {
            System.out.println("\nRPG SIMULATOR");
            System.out.println("1. Crear personaje");
            System.out.println("2. Entrenar");
            System.out.println("3. Batalla");
            System.out.println("4. Inventario");
            System.out.println("5. Estado del personaje");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            // Validación básica: si no es int, limpiar y continuar
            if (!sc.hasNextInt()) {
                System.out.println("Opción inválida");
                sc.nextLine();
                continue;
            }
            opcionMenu = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea

            switch (opcionMenu) {

                // ====== 1) CREAR PERSONAJE (variables + Scanner + if/else) ======
                case 1: {
                    System.out.println("\nCrear personaje");

                    // Nombre (no vacío)
                    System.out.print("Nombre del personaje: ");
                    nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("Nombre inválido (no puede estar vacío).");
                        break;
                    }

                    // Tipo/clase (validar)
                    System.out.print("Tipo (mago/guerrero/arquero): ");
                    clase = sc.nextLine().trim().toLowerCase();

                    if (!(clase.equals("mago") || clase.equals("guerrero") || clase.equals("arquero"))) {
                        System.out.println("Opción inválida (clase no reconocida).");
                        break;
                    }

                    // Vida (no negativa)
                    System.out.print("Puntos de vida (>= 0): ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Opción inválida");
                        sc.nextLine();
                        break;
                    }
                    vida = sc.nextInt();
                    sc.nextLine();
                    if (vida < 0) {
                        System.out.println("Opción inválida (no se permiten negativos).");
                        break;
                    }

                    // Fuerza (no negativa)
                    System.out.print("Fuerza base (>= 0): ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Opción inválida");
                        sc.nextLine();
                        break;
                    }
                    fuerza = sc.nextInt();
                    sc.nextLine();
                    if (fuerza < 0) {
                        System.out.println("Opción inválida (no se permiten negativos).");
                        break;
                    }

                    // Variables extra para “double y boolean”
                    oro = 10.0; // ejemplo simple
                    personajeCreado = true;

                    System.out.println("\n✅ Personaje creado:");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Clase: " + clase);
                    System.out.println("Vida: " + vida);
                    System.out.println("Fuerza: " + fuerza);
                    System.out.println("Oro: " + oro);

                    break;
                }

                // 2) ENTRENAMIENTO (while)
                case 2: {
                    if (!personajeCreado) {
                        System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
                        break;
                    }

                    int opcionEntrenar = -1;
                    System.out.println("\nEntrenamiento");

                    // while: entrena hasta que elija 0
                    while (opcionEntrenar != 0) {
                        System.out.println("\nEntrenar:");
                        System.out.println("1. +5 fuerza");
                        System.out.println("2. +10 vida");
                        System.out.println("0. Terminar entrenamiento");
                        System.out.print("Elige: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Opción inválida");
                            sc.nextLine();
                            continue;
                        }
                        opcionEntrenar = sc.nextInt();
                        sc.nextLine();

                        if (opcionEntrenar == 1) {
                            fuerza += 5;
                            System.out.println("✅ Entrenaste fuerza. Fuerza actual: " + fuerza);
                        } else if (opcionEntrenar == 2) {
                            vida += 10;
                            System.out.println("✅ Entrenaste vida. Vida actual: " + vida);
                        } else if (opcionEntrenar == 0) {
                            System.out.println("🏁 Entrenamiento terminado.");
                        } else {
                            System.out.println("Opción inválida");
                        }
                    }
                    break;
                }

                //3) BATALLA (for 5 turnos)
                case 3: {
                    if (!personajeCreado) {
                        System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
                        break;
                    }

                    System.out.println("\nBatalla (5 turnos)");

                    int vidaJugador = vida;    // copia para esta batalla
                    int vidaEnemigo = 60;      // enemigo fijo simple
                    int fuerzaEnemigo = 12;

                    for (int turno = 1; turno <= 5; turno++) {

                        // Si alguien ya llegó a 0, terminar rápido
                        if (vidaJugador <= 0) {
                            System.out.println("💀 Has sido derrotado antes del turno " + turno + ".");
                            break;
                        }
                        if (vidaEnemigo <= 0) {
                            System.out.println("🏆 ¡Ganaste! El enemigo cayó antes del turno " + turno + ".");
                            break;
                        }

                        // Daños simples (sin random para mantenerlo básico)
                        int danoJugador = (fuerza / 2) + 5;     // depende de tu fuerza
                        int danoEnemigo = (fuerzaEnemigo / 2) + 4;

                        vidaEnemigo -= danoJugador;
                        vidaJugador -= danoEnemigo;

                        // No permitir mostrar negativos
                        if (vidaEnemigo < 0) vidaEnemigo = 0;
                        if (vidaJugador < 0) vidaJugador = 0;

                        System.out.println("\nTurno " + turno + ":");
                        System.out.println("Tú golpeas por " + danoJugador + " de daño. Vida enemigo: " + vidaEnemigo);
                        System.out.println("Enemigo golpea por " + danoEnemigo + " de daño. Tu vida: " + vidaJugador);
                    }

                    // Resultado final si llegaron a turno 5 sin breaks de muerte
                    if (vidaJugador > 0 && vidaEnemigo > 0) {
                        System.out.println("\n⏳ Fin de 5 turnos. Resultado:");
                        if (vidaJugador > vidaEnemigo) {
                            System.out.println("🏁 Ventaja para ti, pero el enemigo sigue en pie.");
                        } else if (vidaEnemigo > vidaJugador) {
                            System.out.println("🏁 El enemigo tiene ventaja, ¡cuidado!");
                        } else {
                            System.out.println("🏁 Empate total. ¡Qué pelea!");
                        }
                    }

                    // (Opcional) Actualizar vida real del personaje después de la batalla:
                    // vida = vidaJugador;

                    break;
                }

                //4) INVENTARIO (foreach)
                case 4: {
                    System.out.println("\nInventario");
                    for (String item : inventario) {
                        System.out.println("- " + item);
                    }
                    break;
                }

                //5) ESTADO (if/else + operadores)
                case 5: {
                    if (!personajeCreado) {
                        System.out.println("\n⚠️ Primero debes crear un personaje (opción 1).");
                        break;
                    }

                    System.out.println("\n--- Estado del personaje ---");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Clase: " + clase);
                    System.out.println("Vida: " + vida);
                    System.out.println("Fuerza: " + fuerza);
                    System.out.println("Oro: " + oro);

                    if (vida > 80) {
                        System.out.println("✅ Estás en excelente estado");
                    } else if (vida > 40) {
                        System.out.println("🟨 Estado moderado");
                    } else {
                        System.out.println("⚠️ Cuidado, estás herido");
                    }

                    // Ejemplo extra de operadores (sin romper reglas)
                    if (fuerza >= 50 && vida >= 50) {
                        System.out.println("💪 Bonus: Te ves fuerte y resistente.");
                    }

                    break;
                }

                //6) SALIR
                case 6: {
                    System.out.println("\n👋 Saliendo del juego... ¡hasta la próxima!");
                    break;
                }

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcionMenu != 6);

        sc.close();
    }
}
