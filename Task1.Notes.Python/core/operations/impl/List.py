from core.repository.JSONmapper import JSONmapper
from core.repository.Note import *
from ..Operation import Operation
from ...repository.db_connector import *


class List(Operation):
    operation = "list"
    def execute(self, path):
        print("\033[H\033[J", end="")
        print("----- Список заметок  -----")
        records = read_all(path)
        notes = list()
        for record in records:
            note = JSONmapper.from_json(record)
            notes.append(note)
        notes.sort(key=Note.get_date)
        for note in notes:
            print(note)
        