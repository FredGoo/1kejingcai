from flask import Flask


app = Flask(__name__, instance_relative_config=True)
app.config.from_pyfile('config.py', silent=True)


@app.route('/hello')
def hello():
    return 'Hello world!'
