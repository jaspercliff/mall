import {reactive, ref} from 'vue'
import {defineStore} from 'pinia'

export const useStepStore = defineStore('step', ()=> {

    const current = ref(1)
    const goods = reactive({
        name:'',
        goodCategoryId: '',
        brandId: '',
        goodAttributeCategoryId:'',
        price: '',
        stock: '',
        sort: '',
        keywords: '',
        isPostFree: 0,
        isPublished: 0,
        picture:'',
        giftGrowth: '0',
        giftPoint: '0',
        isNew: 0,
        isRecommend: 0,
        isVerify: '',
        lowStock: 0,
        promotionStartTime: "2023-07-28 14:17:10",
        promotionEndTime: "2023-07-28 14:17:10",
        promotionPerLimit: '0',
        promotionType: '0',
        promotionPrice: '',
        pointLimit: '0',
        memberPrices: [
            { memberLevelId: 1, memberLevelName: '青铜会员',memberPrice:'0'},
            { memberLevelId: 2, memberLevelName: '白银会员',memberPrice:'0'},
            { memberLevelId: 3, memberLevelName: '黄金会员' ,memberPrice:'0'},
        ],
        goodsFullReductions:[
            {fullPrice:0,reducePrice:0}
        ],
        ladders:[
            {count:0,discount:20}
        ],
        goodsSkuStocks:[
            {
                limit:'',
                price:'',
                promotionPrice:'',
                stock:'',
                spData:''
            }
        ],
        attributeValues:[] as any[]
    })
    return {current,goods}
})
