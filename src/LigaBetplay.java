import java.util.Scanner;

public class LigaBetplay {
  public static void main(String[] args) {
    Node<Team> head = null;
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Seleccione una opción:");
      System.out.println("1. Agregar equipo");
      System.out.println("2. Equipo con menos puntos");
      System.out.println("3. Sumar puntos a un equipo");
      System.out.println("4. Eliminar equipo y sumar 3 puntos a los demás");
      System.out.println("5. Salir");

      int opcion = scanner.nextInt();

      switch (opcion) {
        case 1:
          System.out.print("Nombre del equipo: ");
          String nombreEquipo = scanner.next();
          System.out.print("Puntos del equipo: ");
          int puntos = scanner.nextInt();
          Team team = new Team(nombreEquipo, puntos);
          head = agregarEquipo(head, team);
          break;
        case 2:
          Team equipoMenosPuntos = buscarEquipoConMenosPuntos(head);
          System.out.println("Equipo con menos puntos: " + equipoMenosPuntos.name + " (" + equipoMenosPuntos.points + " puntos)");
          break;
        case 3:
          System.out.print("Nombre del equipo al que se le sumarán puntos: ");
          String nombreEquipoSumar = scanner.next();
          System.out.print("Puntos a sumar: ");
          int puntosASumar = scanner.nextInt();
          head = sumarPuntosAEquipo(head, nombreEquipoSumar, puntosASumar);
          break;
        case 4:
          System.out.print("Nombre del equipo a eliminar: ");
          String nombreEquipoEliminar = scanner.next();
          head = eliminarEquipoYSumarPuntos(head, nombreEquipoEliminar);
          break;
        case 5:
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Opción no válida. Intente de nuevo.");
          break;
      }
    }
  }

  public static Node<Team> agregarEquipo(Node<Team> head, Team team) {
    Node<Team> newNode = new Node<>();
    newNode.object = team;
    newNode.next = null;
    if (head == null) {
      return newNode;
    } else {
      Node<Team> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
      return head;
    }
  }

  public static Team buscarEquipoConMenosPuntos(Node<Team> head) {
    if (head == null) {
      return null;
    }

    Node<Team> current = head;
    Team equipoMenosPuntos = current.object;

    while (current != null) {
      if (current.object.points < equipoMenosPuntos.points) {
        equipoMenosPuntos = current.object;
      }
      current = current.next;
    }
    return equipoMenosPuntos;
  }

  public static Node<Team> sumarPuntosAEquipo(Node<Team> head, String nombreEquipo, int puntosASumar) {
    Node<Team> current = head;

    while (current != null) {
      if (current.object.name.equals(nombreEquipo)) {
        current.object.points += puntosASumar;
        System.out.println("Puntos actualizados para " + nombreEquipo + ": " + current.object.points + " puntos.");
      }
      current = current.next;
    }
    return head;
  }

  public static Node<Team> eliminarEquipoYSumarPuntos(Node<Team> head, String nombreEquipoEliminar) {
    if (head == null) {
      return null;
    }
    if (head.object.name.equals(nombreEquipoEliminar)) {
      return head.next;
    } else {
      Node<Team> current = head;
      Node<Team> previous = null;

      while (current != null && !current.object.name.equals(nombreEquipoEliminar)) {
        previous = current;
        current = current.next;
      }

      if (current != null) {
        previous.next = current.next;
      }
    }

    Node<Team> current = head;
    while (current != null) {
      current.object.points += 3;
      current = current.next;
    }
    System.out.println("Equipo " + nombreEquipoEliminar + " eliminado.");
    return head;
  }

  }
