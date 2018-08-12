from flask_security.utils import encrypt_password
from myapp.data.models import db, User
from myapp import app


def create_users(ctx):
    user1 = User('admin@test.com', '123', True)
    user2 = User('user@test.com', '456', True)

    ctx.session.add(user1)
    ctx.session.add(user2)

    ctx.session.commit()


with app.app_context():
    db.drop_all()
    db.create_all()

    create_users(db)
