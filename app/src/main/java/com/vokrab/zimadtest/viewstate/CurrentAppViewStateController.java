package com.vokrab.zimadtest.viewstate;

import com.vokrab.zimadtest.MainActivity;

/**
 * Created by Олег on 10.08.2015.
 */
public class CurrentAppViewStateController extends ViewStateControllerBase
{
	public CurrentAppViewStateController(MainActivity controller )
	{
		super ( controller );
	}

	@Override
	protected void changeState ( VIEW_STATE newState )
	{
		switch ( newState )
		{
//			case EXEPTION_WORK_RESULT:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				currentFragment = new ExeptionWorkResultViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//			case MY_EXEPTIONS:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				currentFragment = new MyExeptionsViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case RESULT:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				currentFragment = new ResultViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case SIGNS:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				currentFragment = new SearchSignViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case EXAM:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				if ( prevState == VIEW_STATE.RESULT )
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.left_to_center, R.anim.center_to_right );
//				} else
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				}
//				currentFragment = new ExamingView ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//			case TICKET_EDUCATION:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				if ( prevState == VIEW_STATE.RESULT )
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.left_to_center, R.anim.center_to_right );
//				} else
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				}
//				currentFragment = new TicketEducationViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//			case MENU:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				// if ( prevState == VIEW_STATE.SPLASH )
//				// {
//				//
//				// } else
//				// if ( prevState == VIEW_STATE.TICKETS || prevState ==
//				// VIEW_STATE.SIGNS_MENU )
//				// {
//				// fragmentTransaction.setCustomAnimations (
//				// R.anim.down_to_center, R.anim.center_to_up );
//				// } else if ( prevState == VIEW_STATE.EXAM )
//				// {
//				// fragmentTransaction.setCustomAnimations (
//				// R.anim.left_to_center, R.anim.center_to_right );
//				// } else
//				// {
//				// fragmentTransaction.setCustomAnimations (
//				// R.anim.up_to_center, R.anim.center_to_down );
//				// }
//				currentFragment = new MenuViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment, null );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//			case TICKETS:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.up_to_center, R.anim.center_to_down );
//				currentFragment = new TicketsViewFrarment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case SPLASH:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				currentFragment = new SplashViewFragment ();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment, null );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case EXIT:
//			{
//				controller.finish ();
//				break;
//			}
//
//			case RATE:
//			{
//				final String appPackageName = controller.getPackageName ();
//				Intent intent = new Intent ( Intent.ACTION_VIEW );
//				// Try Google play
//				intent.setData ( Uri.parse ( "market://details?id=" + appPackageName ) );
//				if ( !tryStartActivity ( intent ) )
//				{
//					// Market (Google play) app seems not installed, let's try
//					// to open a webbrowser
//					intent.setData ( Uri.parse ( "https://play.google.com/store/apps/details?" + appPackageName ) );
//
//					if ( !tryStartActivity ( intent ) )
//					{
//						// Well if this also fails, we have run out of options,
//						// inform the user.
//						Toast.makeText ( controller, "Could not open Android market, " + "please install the market app.", Toast.LENGTH_SHORT ).show ();
//					}
//				}
//				controller.saveAppRated ();
//				break;
//			}
//			case CHALLENGE_RESULT:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				currentFragment = new ChallengeResultViewFragment();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
//
//			case CHALLENGE_EXAM:
//			{
//				FragmentTransaction fragmentTransaction = manager.beginTransaction ();
//				if ( prevState == VIEW_STATE.RESULT )
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.left_to_center, R.anim.center_to_right );
//				} else
//				{
//					fragmentTransaction.setCustomAnimations ( R.anim.right_to_center, R.anim.center_to_left );
//				}
//				currentFragment = new ChallengeExamFragment();
//				fragmentTransaction.replace ( R.id.content_frame, currentFragment );
//				fragmentTransaction.commitAllowingStateLoss ();
//				break;
//			}
			default:
				break;
		}
	}

	@Override
	protected VIEW_STATE getBackState ( VIEW_STATE currentState )
	{
		switch ( currentState )
		{
			case EXEPTION_WORK_RESULT:
			case EXAM:
			case TICKETS:
			case SIGNS:
			case CHALLENGE_RESULT:
			case CHALLENGE_EXAM:
			case RESULT:
			{
				return VIEW_STATE.MENU;
			}
			case TICKET_EDUCATION:
			{
				return VIEW_STATE.TICKETS;
			}
			case SPLASH:
			{
				return null;
			}
			default:
				return VIEW_STATE.EXIT;// null
		}
	}
}
