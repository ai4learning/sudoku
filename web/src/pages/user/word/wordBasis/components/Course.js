/**
 * 我的课程
 */
import React, {Component} from 'react'
import Antd, { Card, Breadcrumb, Button, Progress } from 'antd'
import fetch from '@common/fetch'

export default class Course extends Component {
  constructor(props) {
    super(props)
    this.state = {
      books: [],
      currentBook: null
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/AjaxGetUserBook',
      method: 'post',
      type: 'json'
    }).then(data => {
      this.setState({
        books: data.books || []
      })
    })
  }

  render() {
    return (
      <div className='word-basis_course'>
        <div className=''>
          <Breadcrumb>
            <Breadcrumb.Item onClick={this.handleClick.bind(this)}>所有课程</Breadcrumb.Item>
            {
              this.state.currentBook ? <Breadcrumb.Item>{this.state.currentBook.bookName}</Breadcrumb.Item> : null
            }
          </Breadcrumb>
        </div>
        <div className='course_list' style={{ display: this.state.currentBook ? 'none' : 'flex' }}>
          {
            this.state.books.map(item => {
              return (
                <Card key={item.bookName} title={item.bookName} hoverable onClick={this.handleBookClick.bind(this, item)}>
                  <div>总单元数：{item.totalUnitNbr}</div>
                  <div>当前学习到：{(item.currentPosition||{}).spelling}</div>
                  <div>当前进度：<Progress percent={parseInt(((item.currentPosition||{}).unitNbr||0)/item.totalUnitNbr*100)}></Progress></div>
                </Card>
              )
            })
          }
        </div>
        <div className='unit_list' style={{ display: this.state.currentBook ? 'flex' : 'none' }}>
          {
            this.state.currentBook ? (this.state.currentBook.CourseUnits || []).map(item => {
              return (
                <Card key={item.Id} title={item.Unit} hoverable>
                  <Button className={item.IsFinished==2?'finished':item.IsFinished==0?'unfinished':'unstarted'} onClick={this.handleWordLearnClick.bind(this, item)}>单词学习</Button>
                  <Button disabled={item.IsFinished == -1} className={item.IsFinished==2?'finished':'unstarted'} onClick={this.handleListenReadClick.bind(this, item)}>听读训练</Button>
                  <Button disabled={item.IsFinished == -1} className={item.IsFinished == -1 ? 'unstarted' : item.IsTested == 1 ? 'finished' : item.IsTested == 2 ? 'unfinished' : 'unstarted'} onClick={this.handleUnitTestClick.bind(this, item)}>单元测试</Button>
                  {/* <Button className='unfinished'>词汇应用</Button> */}
                </Card>
              )
            }) : null
          }
        </div>
      </div>
    )
  }

  handleBookClick(item) {
    fetch({
      url: '/api/Ajax/AjaxLoadCourse',
      method: 'get',
      type: 'json',
      data: {
        moduleCode: item.moduleCode
      }
    }).then(data => {
      this.setState({
        currentBook: data.books[0] || {}
      })
    })
  }

  handleClick() {
    this.setState({
      currentBook: null
    })
  }

  handleWordLearnClick(item) {
    Ka(item.moduleCode + '-' + item.unitNbr)
  }

  handleListenReadClick(item) {
    Cb('sp-' + item.moduleCode + '-' + item.unitNbr)
  }

  handleUnitTestClick(item) {
    window.open('/user/test/unit?moduleCode=' + item.moduleCode + '&unitNbr=' + item.unitNbr)
  }

  handleWordUseClick(item) {
    wordUse('s-' + item.moduleCode + '-' + item.unitNbr)
  }
}