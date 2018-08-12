from flask import Flask, Blueprint
from flask_security import current_user
from myapp.data.models import db
from myapp.admin.controllers import admin

app = Flask(__name__, instance_relative_config=True)

# app.config['DEVELOPMENT']
app.config.from_pyfile('config.cfg')
app.register_blueprint(admin, url_prefix='/admin')

db.init_app(app)
