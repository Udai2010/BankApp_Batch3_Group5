import bcrypt from 'bcryptjs'

export const passwordHashService = (password) => {
    const salt = "$2a$10$EZWqYSG3.NBxmuOON3L4f.";
    const hashedPassword = bcrypt.hashSync(password, salt)
    return hashedPassword;
}