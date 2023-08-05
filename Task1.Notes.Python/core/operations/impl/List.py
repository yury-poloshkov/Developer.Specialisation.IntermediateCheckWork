from ..Operation import Operation
from ...repository.db_connector import *

from datetime import date

class List(Operation):
    operation = "list"
    def execute(self, path):
        print("\033[H\033[J", end="")
        print("----- Создание новой заметки -----")
        note_topic = input("Введите тему: ")
        note_body = input("Введите содержание: ")
        index = str(1)
        today = str(date.today())
        note = index + "; " + today + "; " + note_topic + "; " + note_body
        save_note(path, note)
