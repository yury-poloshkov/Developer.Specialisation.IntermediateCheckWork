def show_menu(path, operations):
    user_choice = "none"
    while user_choice != "exit":
        print("\033[H\033[J", end="")
        print("----- Записная книжка v.2023.07.28 -----")
        print("Connected DB: " + path)
        print("----- ГЛАВНОЕ МЕНЮ --- ")
        # for i in range(0, len(operations)): 
        for operation in operations:
            print("  " + operation.operation.upper())
        user_choice = input("\Введите команду> ").lower()
        for operation in operations:
            if operation.operation == user_choice:
                operation.execute(path)