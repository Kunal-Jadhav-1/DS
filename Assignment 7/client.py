import requests

def fetch_calculations(a, b):
    try:
        response = requests.get("http://localhost:5001/calculate", params={"a": a, "b": b})
        if response.status_code == 200:
            return response.json()
        else:
            print("Error:", response.json().get("error", "Unknown error"))
            return None
    except Exception as e:
        print("Failed to connect to the server:", e)
        return None

def main():
    print("Welcome to Distributed Calculator Client")
    # a = float(input("Enter value of a: "))
    # b = float(input("Enter value of b: "))

    # result = fetch_calculations(a, b)

    while True:
        print("\nChoose an operation:")
        print("1. Access calculator ")
        print("2. Exit")

        choice = input("Enter choice (1-2): ")

        match choice:
            case '1':
                a = float(input("Enter value of a: "))
                b = float(input("Enter value of b: "))
                result = fetch_calculations(a, b)
                print(result)
                # break
            case '2':
                return
            case _:
                print("Invalid choice. Try again.")

if __name__ == "__main__":
    main()