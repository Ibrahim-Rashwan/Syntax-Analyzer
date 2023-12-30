import re

class KeywordDetector:
    keywords = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
        "default", "double", "do", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if",
        "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private",
        "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "void", "volatile", "while"
    }

    @staticmethod
    def detect(word):
        lower_case_word = word.lower()
        special_characters = "!@#$%^&*()-_=+[]{}|;:,.<>?/`~"

        if lower_case_word in KeywordDetector.keywords:
            return f"keyword ({lower_case_word})"
        elif word.isalpha() or word == "_":
            return "identifier"
        elif word.isnumeric():
            return "numeric"
        elif lower_case_word in special_characters:
            return "special character"
        else:
            return "other"

def classify_token(token):
    if token.isnumeric():
        return "numeric"
    elif re.match(r'\b\w+\b', token):
        return KeywordDetector.detect(token)
    elif token in "+-*/%=<>&||^":
        return "operator"
    elif token in "();":
        return "special character"
    elif token.isspace():
        return "space"
    else:
        return "other"

table = {}

if __name__ == "__main__":
    user_input = input("Enter a mathematical expression (e.g., a+a): ")

    # Updated regular expression to handle consecutive special characters
    tokens = re.findall(r'\b\w+\b|[+\-*\/%=<>&|^();]+|.', user_input)

    i = 0
    for token in tokens:
        result = classify_token(token)
        print(f"The token '{token}' is a {result}")

        table[i] = { 'type': result, 'name': token }
        i += 1
    
    print("table: ", table)
