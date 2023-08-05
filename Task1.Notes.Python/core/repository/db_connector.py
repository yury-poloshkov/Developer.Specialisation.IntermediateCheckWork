def create(path):
    try:
        file = open(path, 'r')
    except IOError:
        file = open(path, 'w')
        print('Создана новая записная книжка \> ' + path)
    finally:
        file.close()

def read_all(path):
    file = open(path, 'r')
    notes = list()
    for line in file:
        notes.append(line)
    file.close()
    return notes

def save_all(path, notes):
    file = open(path, 'w')
    file.writelines("%s\n" % note for note in notes)
    file.close()

def save_note(path, new_note):
    file = open(path, 'a')
    file.write(new_note + "\n")
    file.close()    
