from core.repository.JSONmapper import JSONmapper
from core.repository.Note import Note
from ..Operation import Operation
from ...repository.db_connector import *

from datetime import date

class Add(Operation):

    operation = "create"

    def execute(self, path):
        print("\033[H\033[J", end="")
        print("----- Создание новой заметки -----")
        note_topic = input("Введите тему: ")
        note_body = input("Введите содержание: ")
        records = read_all(path)
        # if records != null:
        #     last_note = JSONmapper.from_json(records.pop)

        index = str(1)
        today = str(date.today())
        note = Note(index, today, note_topic, note_body)
        try:
            save_note(path, JSONmapper.to_json(note))
            print("Информация успешно сохранена в " + path)
        except IOError:
            print("ERROR: ошибка сохранения данных")