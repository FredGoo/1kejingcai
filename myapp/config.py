import os
import logging


class BaseConfig(object):
    DEBUG = False
    TESTING = False
    # sqlite :memory: identifier is the default if no filepath is present
    SQLALCHEMY_DATABASE_URI = 'sqlite://'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SECRET_KEY = '1d94e52c-1c89-4515-b87a-f48cf3cb7f0b'
    LOGGING_FORMAT = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
    LOGGING_LOCATION = 'myapp.log'
    LOGGING_LEVEL = logging.DEBUG
    SECURITY_PASSWORD_SALT = '8312hjf123'
    CACHE_TYPE = 'simple'
    COMPRESS_MIMETYPES = ['text/html', 'text/css', 'text/xml',
                          'application/json', 'application/javascript']
    COMPRESS_LEVEL = 6
    COMPRESS_MIN_SIZE = 500
    SUPPORTED_LANGUAGES = {'bg': 'Bulgarian',
                           'en': 'English', 'fr': 'Francais'}
    BABEL_DEFAULT_LOCALE = 'en'
    BABEL_DEFAULT_TIMEZONE = 'UTC'


class DevelopmentConfig(BaseConfig):
    DEBUG = True
    TESTING = False
    ENV = 'dev'
    SQLALCHEMY_DATABASE_URI = 'mysql://root:@localhost/jingcai'
    SECRET_KEY = 'a9eec0e0-23b7-4788-9a92-318347b9a39f'

config = {
    "development": "myapp.config.DevelopmentConfig",
    "default": "myapp.config.DevelopmentConfig"
}


def configure_app(app):
    config_name = os.getenv('FLASK_CONFIGURATION', 'default')
    # object-based default configuration
    app.config.from_object(config[config_name])
    # instance-folders configuration
    app.config.from_pyfile('config.cfg', silent=True)
