from core.repository.JSONmapper import JSONmapper
from core.repository.Note import Note
from ..Operation import Operation
from ...repository.db_connector import *


class List(Operation):
    operation = "list"
    def execute(self, path):
        print("\033[H\033[J", end="")
        print("----- Список заметок  -----")
        records = read_all(path)

        for record in records:
            note = JSONmapper.from_json(record)
            print(note)