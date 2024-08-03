import java.util.*;
public class UserManagerMain{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		UserManager userManager = new UserManager();
		boolean process = true;
		while(process){
			System.out.println("Главное Меню");
			System.out.println("Вам надо выбирать действие:");
			System.out.println("1.Добавить пользователя");
			System.out.println("2.Получить пользователя по идентификатору");
			System.out.println("3.Удалить пользователя по идентификатору");
			System.out.println("4.Наити пользователей по имени");
			System.out.println("5.Выйти из системы"); 
			System.out.println("ВАШ ВЫБОР: ");
			int myChoice = scanner.nextInt();
			scanner.nextLine();
				switch(myChoice){
				case 1: 
					System.out.println("Чтобы добавить пользователя введите имя: ");
					String name = scanner.nextLine();
					int id = userManager.addUser(name);
					System.out.println("Пользователь добавлен успешно! Идентификатор: "+id);
					break;		
			    case 2:
					System.out.println("Введите идентификатор пользователя: ");
					int getID = scanner.nextInt();
					String getName = userManager.getUser(getID);
					if(getName!=null){
						System.out.println("Имя пользователя по данному идентификатору: "+ getName);
					}
					else{
						System.out.println("Пользователь не найден по данному идентификатору");
					}
					break;
				case 3:
					System.out.println("Чтобы удалить пользователя введите идентификатор: ");
					int deleteID = scanner.nextInt();
					boolean success = userManager.deleteUser(deleteID);
					if(success){
						System.out.println("Пользователь успешно удален!");
					}
					else{
						System.out.println("Пользователь не найден");
					}	
					break;
				case 4:
					System.out.println("Введите имя пользователя: ");
					String findName = scanner.nextLine();
					List<Integer> listID = userManager.findUserByName(findName);
					if(listID.isEmpty()){
						System.out.println("Пользователи с таким именем не найдены.");
					}
					else{
						System.out.println("Идентификаторы пользователей с таким именем: ");
						for (int idFound : listID) {
                            System.out.println(idFound);
                        }
					}
					break;
				case 5:
					process = false;
					System.out.println("Вы вышли из системы!");
					break;
				default:
					System.out.println("Неправильный выбор,попробуйте снова!");
					break;
				}
		}
				scanner.close();
	}
	static class UserManager{
		ArrayList<String> users;
		ArrayList<Integer> ids;
		int newId;

		public UserManager(){
			users = new ArrayList<>();
			ids = new ArrayList<>();
			newId = 1;
		}

		public int addUser(String name){
			int id = newId++;
			users.add(name);
			ids.add(id);
			return id;
		}

		public String getUser(int id){
			int index = ids.indexOf(id);
			if(index>=0){
				return users.get(index);
			}
			return null;
		}

		public boolean deleteUser(int id){
			int index = ids.indexOf(id);
			if(index>=0){
				users.remove(index);
				ids.remove(index);
				return true;
			}
			return false;
		}

		public List<Integer> findUserByName(String name){
			List<Integer> foundID = new ArrayList<>();
			for(int i=0;i<users.size();i++){
				if(users.get(i).equals(name)){
					foundID.add(ids.get(i));
				}
			}
			return foundID;
		}
	}
}
