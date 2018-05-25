import React, {Component} from 'react'
import { Collapse, Radio, Button, Input } from 'antd'
import ScoreModal from './ScoreModal'

export default class TestPaper extends Component {
  constructor(props) {
    super(props)

    this.state = {
      dataCh2En: [],
      dataEn2Ch: [],
      dataListen2Ch: [],
      dataListen2Write: [],
      showWrong: false,
      scoreModalVisible: false,
      score: 0
    }
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      dataCh2En: nextProps.dataCh2En || [],
      dataEn2Ch: nextProps.dataEn2Ch || [],
      dataListen2Ch: nextProps.dataListen2Ch || [],
      dataListen2Write: nextProps.dataListen2Write || []
    })
  }

  clearSpellingForAudio(b) {
    for (var c = RegExp("[\\/?\u2026\u2026\uff1f]"), a = "", e, d = 0; d < b.length; d++)
      e = b.substr(d, 1),
        a += e.replace(c, "_");
    return a
  }

  render() {
    return (
      <div className='test_paper'>
        <Collapse defaultActiveKey={['0', '1', '2', '3']}>
          {
            this.state.dataListen2Ch.length ?
              <Collapse.Panel header='【听单词选词义】' key='0'>
                <div className='listen2ch'>
                  {
                    this.state.dataListen2Ch.map((item, index) => {
                      return (
                        <div key={item.question} className='question' style={{ display: this.state.showWrong && item.answer == item.answerIndex ? 'none' : 'block' }}>
                          <div className='question_name' onClick={this.handleAudioClick.bind(this, 'dataListen2Ch', item)}>
                            <img src="/static/image/listenagin_pre.png" />
                            <span>
                              <audio ref={"dataListen2Ch" + item.vocCode}>
                                <source type="audio/mpeg" src={"/soundfile/" + item.spelling.substr(0, 1).toUpperCase() + "/" + this.clearSpellingForAudio(item.spelling) + ".mp3"}></source>
                              </audio>
                            </span>
                          </div>
                          <div className='question_choices'>
                            <Radio.Group onChange={this.handleRadioChange.bind(this, 'dataListen2Ch', index)}>
                              {
                                Object.keys(item.choices).map(value => {
                                  return (
                                    <Radio value={value} key={value}>
                                      <span style={{ color: this.state.showWrong && value == item.answerIndex && item.answer != item.answerIndex ? 'red' : 'inhret' }}>{item.choices[value]}</span>
                                    </Radio>
                                  )
                                })
                              }
                            </Radio.Group>
                          </div>
                        </div>
                      )
                    })
                  }
                </div>
              </Collapse.Panel> : null
          }
          {
            this.state.dataEn2Ch.length ?
              <Collapse.Panel header='【英译汉】' key='1'>
                <div className='en2ch'>
                  {
                    this.state.dataEn2Ch.map((item, index) => {
                      return (
                        <div key={item.question} className='question' style={{ display: this.state.showWrong && item.answer == item.answerIndex ? 'none' : 'block' }}>
                          <div className='question_name'>{item.question}</div>
                          <div className='question_choices'>
                            <Radio.Group onChange={this.handleRadioChange.bind(this, 'dataEn2Ch', index)}>
                              {
                                Object.keys(item.choices).map(value => {
                                  return (
                                    <Radio value={value} key={value}>
                                      <span style={{ color: this.state.showWrong && value == item.answerIndex && item.answer != item.answerIndex ? 'red' : 'inhret' }}>{item.choices[value]}</span>
                                    </Radio>
                                  )
                                })
                              }
                            </Radio.Group>
                          </div>
                        </div>
                      )
                    })
                  }
                </div>
              </Collapse.Panel> : null
          }
          {
            this.state.dataCh2En.length ?
              <Collapse.Panel header='【汉译英】' key='2'>
                <div className='ch2en'>
                  {
                    this.state.dataCh2En.map((item, index) => {
                      return (
                        <div key={item.question} className='question' style={{ display: this.state.showWrong && item.answer == item.answerIndex ? 'none' : 'block' }}>
                          <div className='question_name'>{item.question}</div>
                          <div className='question_choices'>
                            <Radio.Group onChange={this.handleRadioChange.bind(this, 'dataCh2En', index)}>
                              {
                                Object.keys(item.choices).map(value => {
                                  return (
                                    <Radio value={value} key={value}>
                                      <span style={{ color: this.state.showWrong && value == item.answerIndex && item.answer != item.answerIndex ? 'red' : 'inhret' }}>{item.choices[value]}</span>
                                    </Radio>
                                  )
                                })
                              }
                            </Radio.Group>
                          </div>
                        </div>
                      )
                    })
                  }
                </div>
              </Collapse.Panel> : null
          }
          {
            this.state.dataListen2Write.length ?
              <Collapse.Panel header='【听写】' key='3'>
                <div className='listen2write'>
                  {
                    this.state.dataListen2Write.map((item, index) => {
                      return (
                        <div key={item.question} className='question' style={{ display: this.state.showWrong && item.answer == item.spelling ? 'none' : 'block' }}>
                          <div className='question_name' onClick={this.handleAudioClick.bind(this, 'dataListen2Write', item)}>
                            <img src="/content/image/listenagin_pre.png" />
                            <span>
                              <audio ref={"dataListen2Write" + item.vocCode}>
                                <source type="audio/mpeg" src={"/soundfile/" + item.spelling.substr(0, 1).toUpperCase() + "/" + this.clearSpellingForAudio(item.spelling) + ".mp3"}></source>
                              </audio>
                            </span>
                            <span>{item.question}</span>
                          </div>
                          <div className='question_choices'>
                            <Input onChange={this.handleInputChange.bind(this, 'dataListen2Write', index)} />
                            {
                              this.state.showWrong && item.answer != item.spelling ?
                                <span style={{ color: 'red' }}>{item.spelling}</span>
                                : null
                            }
                          </div>
                        </div>
                      )
                    })
                  }
                </div>
              </Collapse.Panel> : null
          }
        </Collapse>
        <div className='actions'>
          {
            this.state.showWrong ?
              <Button type='primary' size='large' onClick={this.handleScoreModalClosePage.bind(this)}>关闭页面</Button>
              :
              <Button type='primary' size='large' onClick={this.handleSubmit.bind(this)}>交卷</Button>
          }
        </div>
        <ScoreModal
          score={this.state.score}
          visible={this.state.scoreModalVisible}
          onCancel={this.handleScoreModalCancel.bind(this)}
          onShowWrong={this.handleScoreModalShowWrong.bind(this)}
          onClosePage={this.handleScoreModalClosePage.bind(this)}
        >
        </ScoreModal>
      </div>
    )
  }

  handleAudioClick(type, item) {
    this.refs[type + item.vocCode].play()
  }

  handleRadioChange(type, index, e) {
    this.state[type][index].answer = e.target.value
  }

  handleInputChange(type, index, e) {
    this.state[type][index].answer = e.target.value
  }

  handleSubmit() {
    let total = (this.state.dataCh2En || []).length + (this.state.dataEn2Ch || []).length + (this.state.dataListen2Ch || []).length + (this.state.dataListen2Write || []).length
    let rightCount = 0
    this.state.dataCh2En.forEach(item => {
      if (item.answer == item.answerIndex) {
        rightCount++
      }
    })
    this.state.dataEn2Ch.forEach(item => {
      if (item.answer == item.answerIndex) {
        rightCount++
      }
    })
    this.state.dataListen2Ch.forEach(item => {
      if (item.answer == item.answerIndex) {
        rightCount++
      }
    })
    this.state.dataListen2Write.forEach(item => {
      if (item.answer == item.spelling) {
        rightCount++
      }
    })
    let score = parseInt(rightCount / total * 100)
    this.setState({
      score: score,
      scoreModalVisible: true
    })
    this.props.onSubmit(score)
  }

  handleScoreModalCancel() {
    this.setState({
      scoreModalVisible: false
    })
  }

  handleScoreModalShowWrong() {
    this.setState({
      scoreModalVisible: false,
      showWrong: true
    })
  }

  handleScoreModalClosePage() {
    this.setState({
      scoreModalVisible: false
    })
    window.close()
  }
}