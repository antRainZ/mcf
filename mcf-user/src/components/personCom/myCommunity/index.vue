<template>
  <div class="main">
    <addCom v-if ="addShow" @getTopic="getTopic"/>
    <manageCom v-else :topic="topic" />
  </div>
</template>

<script>
import addCom from '@/components/personCom/myCommunity/addCom'
import manageCom from '@/components/personCom/myCommunity/manageCom'
import topicApi from '@/api/topic'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: 'Mycommunity',
  components:{
    addCom,
    manageCom
  },
  data () {
    return {
      topic: {
        id: '',
        title: '',
        cover: '',
        description: '',
        fileUrl: '',
        reason: '',
        remark: '',
        status: ''
      },
      addShow: true
    }
  },
  mounted() {
    this.getTopic()
  },
  methods: {
    getTopic() {
      topicApi.myTopic().then(response=>{
        if (response.data ) {
          this.topic = response.data
          this.addShow = false
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>