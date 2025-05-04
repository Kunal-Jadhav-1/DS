from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

@app.route('/calculate', methods=['GET'])
def calculate():
    try:
        a = float(request.args.get('a'))
        b = float(request.args.get('b'))

        # Base URLs for microservices
        base_urls = {
            "addition": "http://localhost:5002/add",
            "subtraction": "http://localhost:5003/subtract",
            "multiplication": "http://localhost:5004/multiply",
            "division": "http://localhost:5005/divide"
        }

        # Prepare results by calling microservices
        result = {}
        for operation, url in base_urls.items():
            response = requests.get(url, params={"a": a, "b": b})
            result[operation] = response.json().get("result")

        return jsonify(result)
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5001)
