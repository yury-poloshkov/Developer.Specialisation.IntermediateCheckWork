class Note:
    def __init__(self, index, date, title, body):
        self.index = index
        self.date = date
        self.title = title
        self.body = body

    def __str__(self):
        return ("№ " + self.index + "\t" + self.date + "\t" + self.title + 
                 "\n" + self.body)
    
    def get_date(self):
        return self.date
    
    def get_index(self):
        return self.index