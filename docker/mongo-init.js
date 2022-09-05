db.createUser(
        {
            user: "mongo",
            pwd: "password",
            roles: [
                {
                    role: "readWrite",
                    db: "scraping"
                }
            ]
        }
);