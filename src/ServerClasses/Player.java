package ServerClasses;

import DatabaseRelatedClasses.Country;
import DatabaseRelatedClasses.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Player
{
    private int id;
    private String nickname;
    private String color;
    private int numOfHackers;
    private int numOfCountries;
    private ArrayList<Country> countries;
    private int numOfWins;
    private int numOfLosses;
    private int numOfBonusCards;
    private int numOfBonusHackers;
    private boolean isOnline;

    public Player( String nickname ) throws SQLException {
        this.id = generatePlayerId();
        this.nickname = nickname;
        this.color = "";
        this.numOfHackers = 0;
        this.numOfCountries = 0;
        this.numOfWins = 0;
        this.numOfLosses = 0;
        this.numOfBonusCards = 0;
        this.numOfBonusHackers = 0;
        this.isOnline = true;
        this.countries = null;
    }

    public Player( int id, String nickname, String color, int numOfHackers, int numOfCountries, ArrayList<Country> countries, int numOfWins, int numOfLosses, int numOfBonusCards, int numOfBonusHackers, boolean isOnline )
    {
        this.id = id;
        this.nickname = nickname;
        this.color = color;
        this.numOfHackers = numOfHackers;
        this.numOfCountries = numOfCountries;
        this.countries = countries;
        this.numOfWins = numOfWins;
        this.numOfLosses = numOfLosses;
        this.numOfBonusCards = numOfBonusCards;
        this.numOfBonusHackers = numOfBonusHackers;
        this.isOnline = isOnline;
    }

    public ArrayList<Country> getCountryListFromString(String countries) throws SQLException
    {
        ArrayList<Country> countriesArray = new ArrayList<>();
        String countryIdStr = "";
        for ( int i = 0; i < countries.length(); i++ )
        {
            if ( countryIdStr.charAt(i) != ',' )
            {
                countryIdStr += countryIdStr.charAt(i);
                continue;
            }
            String query = "SELECT * from country WHERE id='"+countryIdStr+"'";
            Database.connect();
            Database.stmt = Database.conn.createStatement();
            ResultSet rs = Database.stmt.executeQuery(query);
            rs.next();
            Country country = new Country(Integer.parseInt(countryIdStr), countryIdStr, rs.getString("location"));
            countriesArray.add(country);
            countryIdStr = "";
        }
        return countriesArray;
    }

    public int generatePlayerId() throws SQLException
    {
        String query = "SELECT * FROM player";
        System.out.println(Database.connect());
        Database.stmt = Database.conn.createStatement();
        ResultSet rs = Database.stmt.executeQuery(query);
        int randomID = 1 + new Random().nextInt(999999999);
        System.out.println("RANDOM PLAYER ID " + randomID);
        boolean isThere = false;
        while (rs.next())
        {
            if (rs.getInt("id") == randomID)
            {
                isThere = true;
                break;
            }
        }
        if (isThere)
            return -1;
        return randomID;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname( String nickname )
    {
        this.nickname = nickname;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor( String color )
    {
        this.color = color;
    }

    public int getNumOfHackers()
    {
        return numOfHackers;
    }

    public void setNumOfHackers( int numOfHackers )
    {
        this.numOfHackers = numOfHackers;
    }

    public int getNumOfCountries()
    {
        return numOfCountries;
    }

    public void setNumOfCountries( int numOfRegions )
    {
        this.numOfCountries = numOfRegions;
    }

    public ArrayList<Country> getCountries()
    {
        return countries;
    }

    public void setCountries( ArrayList<Country> countries )
    {
        this.countries = countries;
    }

    public boolean isOnline()
    {
        return isOnline;
    }

    public int getNumOfWins()
    {
        return numOfWins;
    }

    public void setNumOfWins( int numOfWins )
    {
        this.numOfWins = numOfWins;
    }

    public int getNumOfLosses()
    {
        return numOfLosses;
    }

    public void setNumOfLosses( int numOfLosses )
    {
        this.numOfLosses = numOfLosses;
    }

    public int getNumOfBonusCards()
    {
        return numOfBonusCards;
    }

    public void setNumOfBonusCards( int numOfBonusCards )
    {
        this.numOfBonusCards = numOfBonusCards;
    }

    public int getNumOfBonusHackers()
    {
        return numOfBonusHackers;
    }

    public void setNumOfBonusHackers( int numOfBonusHackers )
    {
        this.numOfBonusHackers = numOfBonusHackers;
    }

    public boolean getOnline()
    {
        return isOnline;
    }

    public void setOnline( boolean online )
    {
        isOnline = online;
    }
}
