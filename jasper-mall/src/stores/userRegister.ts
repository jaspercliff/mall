import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useUserRegisterStore = defineStore('UserRegister', () => {
    interface User {
        username: string,
        password: string,
        email: string,
    }
    const user = reactive<User>({
        username: '',
        password: '',
        email: '',
    });
    return { user }

})
