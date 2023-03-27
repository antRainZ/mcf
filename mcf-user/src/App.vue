<template>
  <div id="app">
    <Head />
    <router-view/>
    <!-- <Foot /> -->
  </div>
</template>

<script>
import Head from '@/components/pubCom/Head'
import Foot from '@/components/pubCom/Foot'
export default {
  name: 'App',
  components:{
    Head,
    Foot
  }
}
</script>

<style lang="less">
#app{
  height: 100vh;
  background-color: #f4f5f5
}
.index{
  font-size: 16px;
  margin: 0;
  padding: 0;
  box-sizing:border-box;
}
.dis-fx{
  display: flex;
}
.fx-col{
  flex-direction: column;
}
.d-ib{
  display: inline-block;
}
.fx-jc{
  display: flex;
  justify-content: center;
}
.fx-ac{
  display: flex;
  align-items: center;
}
.f-w{
  font-weight: bolder;
}
.mt-0925{
  margin-top: 0.925rem;
}
.mt-40{
  margin-top: 2.5rem;
}
.mt-20{
  margin-top: 1.25rem;
}
.mt-10{
  margin-top: 0.625rem;
}
.mr-5{
  margin-right: 0.3125rem;
}
.mr-10{
  margin-right: 0.625rem;
}
.mb-10{
  margin-bottom: 0.625rem;
}
.mb-20{
  margin-bottom: 1.25rem;
}
.p-5{
  padding: 0.3125rem;
}
.pb-10{
  padding-bottom: 0.625rem;
}
.pt-10{
  padding-top: 0.625rem;
}
.p-20{
  padding: 1.25rem;
}
.p-10{
  padding: 0.625rem;
}

.f-16{
  font-size: 1rem;
}
.f-14{
  font-size: 0.875rem;
}
.f-12{
  font-size: 0.75rem;
}
.f-22{
  font-size: 1.375rem;
}
.ta-c{
  text-align: center;
}
.l-h-12{
  line-height: 1.2;
}
.l-h-15{
  line-height: 1.5;
}.bc-fff{
  background-color: #fff;
}
.c-fff{
  color: #fff;
}
.c-888{
  color: #888;
}
.c-333{
  color: #333;
}
.cur-po{
  cursor: pointer;
}
img{
  width: 100%;
  display: block;
}
input{
  outline: none;
  border: none;
  border: 2px solid hsla(0,0%,59.2%,.2);
}
button{
  outline: none;
  border: none;
  border: 1px solid #007fff;
  background-color: #fff;
  padding: .3rem 1.3rem;
  color: #007fff;
  line-height: 1.5rem;
  cursor: pointer;
}
.baseInput{
  border: 2px solid hsla(0,0%,59.2%,.2);
}
</style>
