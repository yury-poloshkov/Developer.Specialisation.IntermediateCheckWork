from datetime import date
from core.operations.impl.Find import Find
from ..Operation import Operation
from ...repository.db_connector import *


class Change(Operation):
    operation = "edit"
    def execute(self, path):
        print("\033[H\033[J", end="")
        print("----- Редактирование заметки -----")
        if input("Вам известен номер заметки, которую вы хотите отредактировать (y/n): ").lower() == "n":
            operation = Find()
            operation.execute(path)
            print("----- Редактирование заметки -----")
        note_index = input("Введите номер редактируемой заметки: ")
        notes = read_all(path)
        successed = False
        for note in notes:
            if note.index == note_index:
                print("--- нажмите ENTER, если поле не нуждается в корректировке ---")
                note_topic = input("Введите новую тему: ")
                if len(note_topic) != 0:
                    note.title = note_topic
                    note.date = str(date.today())
                    successed = True
                note_body = input("Введите новое содержание: ")
                if len(note_body) != 0:
                    note.body = note_body
                    note.date = str(date.today())
                    successed = True
        if successed == True:
            save_all(path, notes)
            print("Операция выполнена успешно: заметка с номером %s перезаписана" %note_index)
        else:
            print("ERROR: Заметка с номером %s не существует, либо не была скорректирована" %note_index)