package com.padmitriy.android.rndrx.view

import com.padmitriy.android.rndrx.ImmediateSchedulerRule
import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.db.dao.SummitsDao
import com.padmitriy.android.rndrx.model.Summit
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RatingListPresenterTest {

    @get:Rule
    val schedulerRule = ImmediateSchedulerRule()

    @Mock
    lateinit var summitsDao: SummitsDao
    @Mock
    lateinit var view: RatingListView
    @InjectMocks
    lateinit var presenter: RatingListPresenter

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @Test
    fun getSummitsList_ListIsEmpty() {
        `when`(summitsDao.getAllSummits()).thenReturn(Flowable.empty())
        `when`(summitsDao.getAllSummits()).thenReturn(Flowable.empty())

        presenter.getSummitsList()

        verify(view, never()).showMessage(R.string.error_message)
        verify(view, never()).showSummits(ArgumentMatchers.anyList())
    }

    @Test
    fun getSummitsList_ListIsNotEmpty() {
        `when`(summitsDao.getAllSummits()).thenReturn(Flowable.just(TEST_SUMMITS_LIST))

        presenter.getSummitsList()

        verify(view, never()).showMessage(R.string.error_message)
        verify(view, atLeastOnce()).showSummits(TEST_SUMMITS_LIST)
    }

    @Test
    fun fillSummitList_methodCalled() {
        presenter.fillSummitList()

        verify(view, never()).showMessage(R.string.error_message)
        verify(summitsDao, atLeastOnce()).insertSummitsList(ArgumentMatchers.anyList())
    }

    @Test
    fun changeSummitRating_methodCalled() {
        presenter.changeSummitRating(TEST_SUMMIT_DTO)

        verify(view, never()).showMessage(R.string.error_message)
        verify(summitsDao, atLeastOnce()).updateSummit(TEST_SUMMIT_DTO)
    }

    @Test(timeout = 5000)
    fun startRandomizing_methodsCalled() {
        `when`(summitsDao.getSummitById(ArgumentMatchers.anyInt())).thenReturn(Single.just(TEST_SUMMIT_DTO))
        //hack to stop loop
        `when`(summitsDao.updateSummit(TEST_SUMMIT_DTO)).thenThrow(Exception::class.java)

        presenter.startRandomizing()

        verify(view, never()).showMessage(R.string.error_message)
        verify(summitsDao, atLeastOnce()).updateSummit(TEST_SUMMIT_DTO)
        verify(summitsDao, atLeastOnce()).getSummitById(ArgumentMatchers.anyInt())
    }

    @Test
    fun getRandomId_generates() {
        val result = presenter.getRandomId()
        Assert.assertTrue(result > 0)
        Assert.assertTrue(result <= 10)
    }

    @Test
    fun getRandomRating_generates() {
        val result = presenter.getRandomRating()
        Assert.assertTrue(result >= 0)
        Assert.assertTrue(result <= 5)
    }

    @Test
    fun getRandomDelay_generates() {
        val result = presenter.getRandomDelay()
        Assert.assertTrue(result >= 0)
    }

    @After
    fun tearDown() {
        presenter.dropView()
    }

    companion object {
        private val TEST_SUMMITS_LIST = arrayListOf<Summit>(Summit("", 0.0f, 0.0f, ""))
        private val TEST_SUMMIT_DTO = Summit("", 0.0f, 0.0f, "")
    }
}
