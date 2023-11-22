import {defineStore} from "pinia";
import {ref} from "vue";

export const useUserStore = defineStore("user",()=>{
    interface user{
        name:'',
    }
    const username = ref<String>()
    function getUserName(){
        return username.value;
    }

    function setUserName(username1:string){
        username.value = username1
    }
    return {getUserName,setUserName}
})