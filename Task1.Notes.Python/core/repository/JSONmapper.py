import json
from core.repository.Note import Note

class JSONmapper:
    @staticmethod
    def to_json(note):
        if isinstance(note, Note):
            return json.dumps({
                "index": note.index,
                "date": note.date,
                "title": note.title,
                "body": note.body,
                })
    @staticmethod
    def from_json(record):
        record = json.loads(record)
        try:
            index = Note(record["index"])
            date = Note(record["date"])
            title = Note(record["title"])
            body = Note(record["body"])
            note = Note(index, date, title, body)
            return note
        except AttributeError:
            print("Неверная структура")